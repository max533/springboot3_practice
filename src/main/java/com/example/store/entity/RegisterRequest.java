package com.example.store.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RegisterRequest {
    @NotBlank
    @NotNull
    @Schema(description = "the account of the user (使用者帳號)", maxLength = 128, example = "lalamove")
    private String account;

    @NotBlank
    @NotNull
    @Schema(description = "the password of the user (使用者密碼)", maxLength = 128, example = "helloworld")
    private String password;

    @NotBlank
    @NotNull
    @Schema(description = "the name of the user (使用者名稱)", maxLength = 128, example = "Sam")
    private String name;

    @NotBlank
    @NotNull
    @Schema(
            description = "the role of the user (使用者角色) (Only accept USER / ADMIN)",
            defaultValue = "USER",
            example = "USER"
    )
    private String role;

}