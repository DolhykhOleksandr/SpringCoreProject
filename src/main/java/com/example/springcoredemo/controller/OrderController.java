package com.example.springcoredemo.controller;



import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.service.OrderService;
import com.example.springcoredemo.utils.Util;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Integer id) {
        return orderService.get(id);
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO) {
        if (Util.anyNull(orderDTO.getDate()))
            throw new IllegalArgumentException("Can't save order without date");
        orderService.save(orderDTO);
    }

    @PutMapping
    public void update(@RequestBody OrderDTO orderDTO) {
        if (Util.anyNull(orderDTO.getId()))
            throw new IllegalArgumentException("Can't update order without id");
        orderService.update(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

}