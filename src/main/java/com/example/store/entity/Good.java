package com.example.store.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Jeff
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Good {
    @NotNull
    @Schema(name = "id", description = "the identity of the good (UUID)")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID _id;

    @NotBlank
    @NotNull
    @Schema(description = "the name of the good (商品名稱)", maxLength = 128, example = "Tesla")
    @Column(name = "name", length = 128, nullable = false)
    private String goods_name;
}
