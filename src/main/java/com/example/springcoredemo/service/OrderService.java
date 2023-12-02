package com.example.springcoredemo.service;

import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(int id);

    OrderDto addOrder(OrderDto orderDto);

    OrderDto removeOrder(int id);

    OrderDto updateOrder(OrderDto updatedOrderDto);

    List<ProductDto> getAllByOrderId(int orderId);

    ProductDto addProduct(int orderId, ProductDto productDto);

    ProductDto removeProduct(int orderID, int productId);

    ProductDto updateProduct(int orderId, ProductDto newProductDto);

    double calculateCost(OrderDto orderDto);
}
