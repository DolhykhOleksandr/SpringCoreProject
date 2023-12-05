package com.example.springcoredemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Order {

    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<Product> products;

}
