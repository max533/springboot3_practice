package com.example.store.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jeff
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank
    @Schema(description = "the account of the user (使用者帳號)", maxLength = 128, example = "bassid")
    private String account;

    @NotBlank
    @Schema(description = "the password of the user (使用者密碼)", maxLength = 128, example = "password")
    String password;
}
