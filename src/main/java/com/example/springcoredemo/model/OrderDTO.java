package com.example.springcoredemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Integer id;
    private LocalDate date;
    private Double cost;
    @JsonProperty("products")
    private List<ProductDTO> productDTOS;

}