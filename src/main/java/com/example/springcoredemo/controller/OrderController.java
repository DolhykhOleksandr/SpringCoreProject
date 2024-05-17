package com.example.springcoredemo.controller;

import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.service.OrderService;
import com.example.springcoredemo.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springcoredemo.repository.OrderRepository;

import java.util.List;

@Api(tags = "Order")
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @ApiOperation("Get order by ID")
    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Integer id) {
        return orderService.get(id);
    }

    @ApiOperation("Get all orders")
    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @ApiOperation("Create a new order")
    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO) {
        if (Util.anyNull(orderDTO.getDate())) {
            throw new IllegalArgumentException("Can't save order without date");
        }

        OrderDTO savedOrderDTO = orderService.save(orderDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDTO);
    }

    @ApiOperation("Update an existing order")
    @PutMapping
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO orderDTO) {
        if (orderDTO.getId() == null) {
            throw new IllegalArgumentException("Can't update order without id");
        }

        OrderDTO updatedOrderDTO = orderService.update(orderDTO);

        return ResponseEntity.ok(updatedOrderDTO);
    }

    @ApiOperation("Delete an order")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with id: " + id);
        }

        orderService.delete(id);

        return ResponseEntity.ok("Order with ID " + id + " deleted successfully");
    }
}
