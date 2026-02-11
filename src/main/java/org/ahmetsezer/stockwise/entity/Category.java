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

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
