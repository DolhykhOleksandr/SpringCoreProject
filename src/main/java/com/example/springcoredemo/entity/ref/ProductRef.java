package com.example.springcoredemo.entity.ref;


import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;


@Table("order_product")
@Data
@AllArgsConstructor
public class ProductRef {

    private Integer productId;

}