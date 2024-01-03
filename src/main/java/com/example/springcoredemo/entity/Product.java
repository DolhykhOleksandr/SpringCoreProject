package com.example.springcoredemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCT")
@Data
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Column("PRODUCT_ID")
    private Integer productId;

    @Column("NAME")
    private String name;

    @Column("COST")
    private Double cost;

}