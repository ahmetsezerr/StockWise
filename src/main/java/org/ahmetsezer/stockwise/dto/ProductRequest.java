package org.ahmetsezer.stockwise.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    private String sku;
    @Size(min = 3, max = 100, message = "Açıklama 3-100 karakter olmalı")
    private String description;

    @Positive(message = "Fiyat 0'dan büyük olmalıdır")
    private BigDecimal price;

    @Min(value = 0, message = "Stok adedi 0'dan az olamaz")
    private Integer stockQuantity;

    private Long categoryId;
    private Long supplierId;
}