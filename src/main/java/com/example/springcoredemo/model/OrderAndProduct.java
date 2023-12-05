package com.example.springcoredemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderAndProduct {

    private Integer orderId;
    private Integer productId;

}