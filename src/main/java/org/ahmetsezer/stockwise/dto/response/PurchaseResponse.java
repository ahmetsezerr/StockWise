package org.ahmetsezer.stockwise.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data

public class PurchaseResponse {

    private Long id;

    private Long productId;
    private String productName;

    private Long supplierId;
    private String supplierName;

    private Integer quantity;
    private BigDecimal unitPrice;

    private BigDecimal totalAmount;

    private LocalDateTime purchaseDate;
}

