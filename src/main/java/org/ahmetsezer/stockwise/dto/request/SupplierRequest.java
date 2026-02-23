package org.ahmetsezer.stockwise.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank(message = "Supplier name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(
            regexp = "^[0-9+\\-() ]{10,20}$",
            message = "Phone format is invalid"
    )
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid")
    private String email;
}
