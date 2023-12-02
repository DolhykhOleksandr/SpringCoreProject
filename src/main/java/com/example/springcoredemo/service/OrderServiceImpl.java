package com.example.springcoredemo.service;


import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;
import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderDto> getAll() {
        List<OrderDto> orders = jdbcTemplate.query("SELECT * FROM Orders", new BeanPropertyRowMapper<>(OrderDto.class));
        for (OrderDto order : orders) {
            order.setProducts(getAllProductsByOrderId(order.getId()));
        }
        return orders;
    }

    public OrderDto getById(int id) {
        OrderDto order = jdbcTemplate.queryForObject("SELECT * FROM Orders WHERE id=" + id,
                new BeanPropertyRowMapper<>(OrderDto.class));
        if (order != null) {
            order.setProducts(getAllProductsByOrderId(id));
        }
        return order;
    }

    public OrderDto getByUsername(String username) {
        OrderDto order = jdbcTemplate.queryForObject("SELECT * FROM Orders WHERE name='" + username + "'",
                new BeanPropertyRowMapper<>(OrderDto.class));
        if (order != null) {
            order.setProducts(getAllProductsByOrderId(order.getId()));
        }
        return order;
    }

    public OrderDto add(OrderDto order) {
        double cost = calculateOrderCost(order);
        jdbcTemplate.update("INSERT INTO Orders VALUES (DEFAULT, ?, ?, ?)",
                order.getName(), order.getDate(), cost);
        return order;
    }

    public List<ProductDto> getAllProductsByOrderId(int orderId) {
        return jdbcTemplate.query("SELECT * FROM Products WHERE order_id=" + orderId,
                new BeanPropertyRowMapper<>(ProductDto.class));
    }

    public List<ProductDto> getAllProductsByUsername(String username) {
        OrderDto order = getByUsername(username);
        return jdbcTemplate.query("SELECT * FROM Products WHERE order_id=" + order.getId(),
                new BeanPropertyRowMapper<>(ProductDto.class));
    }

    public ProductDto addProductToOrder(int orderId, ProductDto product) {
        product.setOrderId(orderId);
        OrderDto order = getById(orderId);
        double orderCost = calculateOrderCost(order) + product.getCost();
        jdbcTemplate.update("INSERT INTO Products VALUES (DEFAULT, ?, ?, ?)",
                product.getName(), product.getCost(), product.getOrderId());
        jdbcTemplate.update("UPDATE Orders SET cost = ? WHERE id = ?", orderCost, orderId);
        return product;
    }

    public double calculateOrderCost(OrderDto order) {
        List<ProductDto> products = order.getProducts();
        if (products == null || products.isEmpty()) {
            return 0.0;
        } else {
            double productsCost = 0.0;
            for (ProductDto product : products) {
                productsCost += product.getCost();
            }
            return productsCost;
        }
    }

}