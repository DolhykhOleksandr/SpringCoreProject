package com.example.springcoredemo.repository.dao;

import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.model.OrderAndProduct;

public interface OrderAndProductDao {
    void save(OrderAndProduct orderAndProduct);

    void save(Order order);

    void delete(Order order);

}