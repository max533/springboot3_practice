package com.example.store.controller;

import com.example.store.entity.AuthenticationRequest;
import com.example.store.entity.AuthenticationResponse;
import com.example.store.entity.RegisterRequest;
import com.example.store.entity.SystemUser;
import com.example.store.service.AuthenticationService;
import com.example.store.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author max533
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Authentication",
        description = "The Authentication API. Contains all the operations like register, login, etc."
)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final String authHeader = "Authorization";

    @Operation(
            summary = "Register a user with details",
            description = "Register a user with details (註冊會員)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request (錯誤請求)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        SystemUser systemUser = authenticationService.register(request);

        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .account(systemUser.getAccount())
                .name(systemUser.getName())
                .build();

        String jwtToken = jwtService.generateToken(systemUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(authHeader, jwtToken)
                .body(authenticationResponse);
    }

    @Operation(
            summary = "Login as an existed user",
            description = "Login as an existed user with username and password " +
                    "(用戶登入系統並在 Response Header Authorization 取得 token)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized (帳號密碼錯誤)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        SystemUser systemUser = authenticationService.authenticate(request);
        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .account(systemUser.getAccount())
                .name(systemUser.getName())
                .build();
        String jwtToken = jwtService.generateToken(systemUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(authHeader, jwtToken)
                .body(authenticationResponse);
    }


}
