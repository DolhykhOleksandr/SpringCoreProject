package com.example.springcoredemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;
    private String name;
    private double cost;


}


