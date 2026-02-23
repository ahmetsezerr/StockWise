package org.ahmetsezer.stockwise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "purchases")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase extends BaseEntity{


    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    private Integer quantity;

    private BigDecimal unitPrice;

    private LocalDateTime purchaseDate;


}
