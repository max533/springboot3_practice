package com.example.store.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AuthenticationResponse {
    @Schema(description = "the account of the user (使用者帳號)", maxLength = 128, example = "bassid")
    private String account;
    @Schema(description = "the name of the user (使用者名稱)", maxLength = 128, example = "Jeff")
    private String name;
}