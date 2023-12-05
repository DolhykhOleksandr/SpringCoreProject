package com.example.springcoredemo.repository.dao;

import com.example.springcoredemo.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Optional<Order> getById(int id);

    List<Order> getAllOrders();

    Integer save(Order order);

    void update(Order order);

    void delete(Integer id);

}
