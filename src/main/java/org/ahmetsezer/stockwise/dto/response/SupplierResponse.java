package org.ahmetsezer.stockwise.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplierResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private BigDecimal totalPurchaseAmount;
    private Long totalPurchaseCount;

}
