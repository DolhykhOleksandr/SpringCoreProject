package com.example.springcoredemo.entity.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ORDER_PRODUCT")
@Data
@AllArgsConstructor
public class ProductRef {

    @Column("PRODUCT_ID")
    private Integer productId;

}