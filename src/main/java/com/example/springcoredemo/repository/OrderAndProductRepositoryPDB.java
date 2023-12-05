package com.example.springcoredemo.repository;

import com.example.springcoredemo.exception.GlovoDaoException;
import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.model.OrderAndProduct;
import com.example.springcoredemo.repository.dao.OrderAndProductDao;
import com.example.springcoredemo.repository.queries.OrderAndProductQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository("orderAndProductPDB")
public class OrderAndProductRepositoryPDB implements OrderAndProductDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderAndProductRepositoryPDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(OrderAndProduct orderAndProduct) {
        try {
            jdbcTemplate.update(
                    OrderAndProductQuery.SAVE_ORDER_PRODUCT.getValue(),
                    orderAndProduct.getOrderId(),
                    orderAndProduct.getProductId());
        } catch (DataAccessException ex) {
            throw new GlovoDaoException(ex.getMessage());
        }

    }

    @Override
    public void save(Order order) {
        try {
            jdbcTemplate.batchUpdate(
                    OrderAndProductQuery.SAVE_ORDER_PRODUCT.getValue(),
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setInt(1, order.getId());
                            ps.setInt(2, order.getProducts().get(i).getId());
                        }

                        public int getBatchSize() {
                            return order.getProducts().size();
                        }

                    });
        } catch (DataAccessException ex) {
            throw new GlovoDaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(Order order) {
        try {
            jdbcTemplate.update(OrderAndProductQuery.DELETE_ORDER_PRODUCT.getValue(), order.getId());
        } catch (DataAccessException ex) {
            throw new GlovoDaoException(ex.getMessage());
        }

    }
}