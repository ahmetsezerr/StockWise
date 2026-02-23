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

    private String name;
    private String phone;

    @Column(unique = true)
    private String email;

    private boolean active = true;

    @OneToMany(mappedBy = "supplier")
    private List<Purchase> purchases;





}
