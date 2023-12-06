package com.example.springcoredemo.controller.web;


import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/v1/orders")
public class OrderWebController {

    private final OrderService orderService;

    public OrderWebController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public @ResponseBody Order get(@PathVariable Integer id) {
        return orderService.getOrderById(id).orElse(null);
    }

    @GetMapping
    public String getFrontPage() {
        return "index";
    }

    @GetMapping("/{id}/products")
    public String getProductsForOrder(@PathVariable Integer id, Model model) {
        Optional<Order> order = orderService.getOrderById(id);
        model.addAttribute(order.get());
        return "productsByOrder";
    }

}