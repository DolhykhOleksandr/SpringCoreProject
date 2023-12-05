package com.example.springcoredemo.service;

import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.model.OrderAndProduct;
import com.example.springcoredemo.repository.dao.OrderAndProductDao;
import com.example.springcoredemo.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class OrderAndProductService {

    private OrderAndProductDao orderAndProductDao;

    public OrderAndProductService(OrderAndProductDao orderAndProductDao) {
        this.orderAndProductDao = orderAndProductDao;
    }

    public void save(Order order) {
        if (Util.checkNull(order, order.getId(), order.getProducts()))
            throw new IllegalArgumentException("Can't save null orderAndProduct connections");

        orderAndProductDao.save(order);
    }

    public void save(OrderAndProduct orderAndProduct) {
        if (Util.checkNull(orderAndProduct, orderAndProduct.getProductId(), orderAndProduct.getOrderId()))
            throw new IllegalArgumentException("Can't save null orderAndProduct connection");

        orderAndProductDao.save(orderAndProduct);
    }

    public void delete(Order order) {
        if (Util.checkNull(order, order.getId()))
            throw new IllegalArgumentException("Can't delete null orderAndProduct connections");

        orderAndProductDao.delete(order);
    }

}