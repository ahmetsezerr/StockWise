package org.ahmetsezer.stockwise.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Category name cannot be blank")
    private String name;
    @Size(min = 3, max = 100, message = "Açıklama 3-100 karakter olmalı")
    private String description;
}
