package org.ahmetsezer.stockwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Table(name = "products")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;

    @Column(unique = true)
    private String sku;



    @OneToMany(mappedBy = "product")
    private List<SaleItem> saleItems;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "product")
    private List<StockMovement> stockMovements;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
