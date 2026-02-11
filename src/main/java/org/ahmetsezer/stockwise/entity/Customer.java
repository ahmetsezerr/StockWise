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

@Getter
@Setter
@Table(name = "customer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity
{
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String address;

    @OneToMany(mappedBy = "customer")
    private  List<Sale> sales;
}
