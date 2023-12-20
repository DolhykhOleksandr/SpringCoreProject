package com.example.springcoredemo.entity;


import com.example.springcoredemo.entity.ref.ProductRef;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Set;

@Table("order")
@Data
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private Integer orderId;
    private LocalDate date;
    private Double cost;
    @MappedCollection(idColumn = "ORDER_ID")
    private Set<ProductRef> products;

    public void addProduct(Product product) {
        products.add(new ProductRef(product.getProductId()));
    }

}