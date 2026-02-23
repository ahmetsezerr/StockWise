package org.ahmetsezer.stockwise.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "categories")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Category extends BaseEntity {

    private String name;
    private  String description;
    private boolean active = true;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;
}
