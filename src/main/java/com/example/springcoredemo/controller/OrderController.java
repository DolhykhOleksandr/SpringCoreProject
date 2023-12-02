package com.example.springcoredemo.controller;

import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;
import com.example.springcoredemo.service.OrderServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @DeleteMapping("/{id}")
    public OrderDto removeOrder(@PathVariable int id) {
        return orderService.removeOrder(id);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @GetMapping("/{orderId}/products")
    public List<ProductDto> getAllByOrderId(@PathVariable int orderId) {
        return orderService.getAllByOrderId(orderId);
    }

    @PostMapping("/{orderID}/products")
    public ProductDto addProduct(@PathVariable int orderID, @RequestBody ProductDto productDto) {
        return orderService.addProduct(orderID, productDto);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ProductDto removeProduct(@PathVariable int orderId, @PathVariable int productId) {
        return orderService.removeProduct(orderId, productId);
    }

    @PutMapping("/{orderId}/products")
    public ProductDto updateProduct(@PathVariable int orderId, @RequestBody ProductDto productDto) {
        return orderService.updateProduct(orderId, productDto);
    }
}