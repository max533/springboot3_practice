package com.example.store.controller;

import com.example.store.entity.CreateGoodRequest;
import com.example.store.entity.Good;
import com.example.store.service.GoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author Jeff
 */

@RestController
@RequestMapping("/api/v1/")
@SecurityRequirement(name = "JWT Authentication")
@Tag(name = "Good", description = "The Good API. Contains all the operations that can be performed on a good.")
@RequiredArgsConstructor
public class GoodController {

    @Autowired
    GoodService goodService;

    @Operation(
            summary = "Fetch good collection",
            description = "Fetch good collection (取得所有商品)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Good.class))
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden (權限不足)", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content})
    })
    @GetMapping("/goods")
    public Iterable<Good> getGoodList() {
        return goodService.getGoodCollection();
    }


    @Operation(
            summary = "Fetch good with a given id",
            description = "Fetch a specific good with a given id (取得指定商品)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Good.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden (權限不足)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @GetMapping("/goods/{id}")
    public Good getGood(
            @Parameter(description = "the identity of the good", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
            @PathVariable
            UUID id
    ) {
        return goodService.getGood(id);
    }


    @Operation(
            summary = "Create a good",
            description = "Create a new good (新增商品)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Good.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request (錯誤請求)", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden (權限不足)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @PostMapping("/goods/add")
    public ResponseEntity<Good> createGood(@RequestBody CreateGoodRequest createGoodRequest) {
        Good good = Good.builder()
                .goods_name(createGoodRequest.getGoods_name())
                .build();
        Good goodInstance = goodService.createGood(good);
        return ResponseEntity.status(HttpStatus.CREATED).body(goodInstance);
    }


    @Operation(
            summary = "Update a specific good detail",
            description = "Update a specific good detail with a given id (更新商品)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Good.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request (錯誤請求)", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden (權限不足)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @PutMapping("/goods/{id}")
    public ResponseEntity<Good> updateGood(
            @Parameter(description = "the identity of the good", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
            @PathVariable
            UUID id,
            @RequestBody
            Good good
    ) {
        Good goodInstance = goodService.updateGood(id, good);
        return ResponseEntity.status(HttpStatus.OK).body(goodInstance);
    }


    @Operation(
            summary = "Delete a specific good",
            description = "Delete a specific good with a given id (刪除商品)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success (成功)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Good.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden (權限不足)", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error (系統錯誤)", content = {@Content()})
    })
    @DeleteMapping("/goods/{id}")
    public ResponseEntity<?> deleteGood(
            @Parameter(description = "the identity of the good", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
            @PathVariable
            UUID id
    ) {
        goodService.deleteGood(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
