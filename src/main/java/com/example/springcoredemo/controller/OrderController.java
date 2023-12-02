package com.example.springcoredemo.controller;

import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;
import com.example.springcoredemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/id/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @GetMapping("/username/{username}")
    public OrderDto getByUsername(@PathVariable String username) {
        return orderService.getByUsername(username);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto order) {
        return orderService.add(order);
    }

    @GetMapping("/id/{orderId}/products")
    public List<ProductDto> getAllProductsByOrderId(@PathVariable int orderId) {
        return orderService.getAllProductsByOrderId(orderId);
    }

    @GetMapping("/username/{username}/products")
    public List<ProductDto> getAllProductsByUsername(@PathVariable String username) {
        return orderService.getAllProductsByUsername(username);
    }

    @PostMapping("/{orderID}/products")
    public ProductDto addProductToOrder(@PathVariable int orderID, @RequestBody ProductDto product) {
        return orderService.addProductToOrder(orderID, product);
    }
}