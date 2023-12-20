package com.example.springcoredemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
@Data
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private Integer productId;
    private String name;
    private Double cost;

}