package com.example.springcoredemo.repository;

import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.repository.dao.OrderDao;
import com.example.springcoredemo.repository.mapper.OrderRowMapper;
import com.example.springcoredemo.repository.mapper.ProductRowMapper;
import com.example.springcoredemo.repository.queries.OrderQuery;
import com.example.springcoredemo.repository.queries.ProductQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository("orderRepoPDB")
public class OrderRepositoryPDB implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryPDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> getById(int id) {
        return jdbcTemplate.query(
                        OrderQuery.GET_ORDER_BY_ID.getValue(),
                        new OrderRowMapper(),
                        id)
                .stream()
                .findAny()
                .map(o -> {
                    o.setProducts(jdbcTemplate.query(
                            ProductQuery.GET_PRODUCT_BY_ORDER.getValue(),
                            new ProductRowMapper(),
                            o.getId()));
                    return o;
                });
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = jdbcTemplate.query(
                OrderQuery.GET_ALL_ORDERS.getValue(),
                new OrderRowMapper());

        for (Order order : orders) {
            order.setProducts(jdbcTemplate.query(ProductQuery.GET_PRODUCT_BY_ORDER.getValue(), new ProductRowMapper(), order.getId()));
        }
        return orders;
    }

    @Override
    public Integer save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderQuery.SAVE_ORDERS.getValue(), Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(order.getDate()));
            ps.setDouble(2, order.getCost());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(Order order) {
        jdbcTemplate.update(OrderQuery.UPDATE_ORDER.getValue(), order.getDate(), order.getCost(), order.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(OrderQuery.DELETE_ORDER.getValue(), id);
    }
}
