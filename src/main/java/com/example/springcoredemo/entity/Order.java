package com.example.springcoredemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_table")
public class Order {

    @SequenceGenerator(
            name = "order_seq",
            sequenceName = "order_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_seq"
    )
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "cost")
    private Double cost;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", cost=" + cost +
                '}';
    }
}