package com.example.springcoredemo.service;

import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(int id);

    OrderDto getByUsername(String username);

    OrderDto add(OrderDto order);

    List<ProductDto> getAllProductsByOrderId(int orderId);

    List<ProductDto> getAllProductsByUsername(String username);

    ProductDto addProductToOrder(int orderId, ProductDto product);

    double calculateOrderCost(OrderDto order);
}
