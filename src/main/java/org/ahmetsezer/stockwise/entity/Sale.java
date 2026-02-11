package org.ahmetsezer.stockwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "sales")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sale extends BaseEntity{
    private BigDecimal totalPrice;
    private LocalDateTime saleDate;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "sale")
    private List <SaleItem> saleItems;
}
