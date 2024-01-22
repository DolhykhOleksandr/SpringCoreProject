package com.example.springcoredemo.controller;


import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.service.OrderService;
import com.example.springcoredemo.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springcoredemo.repository.OrderRepository;

import java.util.List;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
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
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO) {
        if (Util.anyNull(orderDTO.getDate())) {
            throw new IllegalArgumentException("Can't save order without date");
        }

        Integer orderId = orderDTO.getId();


        if (orderId != null && orderRepository.existsById(orderId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order with id " + orderId + " already exists");
        }

        OrderDTO savedOrderDTO = orderService.save(orderDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDTO);
    }


    @PutMapping
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO orderDTO) {
        if (orderDTO.getId() == null) {
            throw new IllegalArgumentException("Can't update order without id");
        }

        OrderDTO updatedOrderDTO = orderService.update(orderDTO);

        return ResponseEntity.ok(updatedOrderDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with id: " + id);
        }

        orderService.delete(id);

        return ResponseEntity.ok("Order deleted successfully");
    }
}
