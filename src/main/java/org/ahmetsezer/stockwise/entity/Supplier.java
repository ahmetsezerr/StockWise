package org.ahmetsezer.stockwise.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "suppliers")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends BaseEntity{
    private String companyName;
    private String contactName;
    private String phoneNumber;

    @Column(unique = true)
    private String email;


    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
