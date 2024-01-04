package com.example.springcoredemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq"
    )
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

}