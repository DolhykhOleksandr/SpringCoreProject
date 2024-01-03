package com.example.springcoredemo.entity;



import com.example.springcoredemo.entity.ref.ProductRef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Set;


@Table("ORDER")
@Data
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @Column("ORDER_ID")
    private Integer orderId;

    @Column("DATE")
    private LocalDate date;

    @Column("COST")
    private Double cost;
    @MappedCollection(idColumn = "ORDER_ID")
    private Set<ProductRef> products;

    public void addProduct(Product product) {
        products.add(new ProductRef(product.getProductId()));
    }

}