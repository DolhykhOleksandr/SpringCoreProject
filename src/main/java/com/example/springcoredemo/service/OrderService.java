package com.example.springcoredemo.service;

import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.model.OrderAndProduct;
import com.example.springcoredemo.model.Product;
import com.example.springcoredemo.repository.dao.OrderDao;
import com.example.springcoredemo.utils.Util;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final OrderAndProductService orderAndProductService;
    private final TransactionTemplate transactionTemplate;

    public OrderService(@Qualifier("orderRepoPDB") OrderDao orderDao,
                        OrderAndProductService orderAndProductService,
                        PlatformTransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.orderAndProductService = orderAndProductService;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderDao.getById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public void saveOrder(Order order) {
        if (Util.checkNull(order, order.getDate(), order.getCost()))
            throw new IllegalArgumentException("Can't save null order");


        transactionTemplate.executeWithoutResult(action -> {
            Integer orderId = orderDao.save(order);

            for (Product product : order.getProducts()) {
                if (product.getId() != null) {
                    orderAndProductService.save(new OrderAndProduct(orderId, product.getId()));
                }
            }
        });
    }

    public void updateOrder(Order order) {
        if (Util.checkNull(order, order.getDate(), order.getCost(), order.getId()))
            throw new IllegalArgumentException("Can't update null order");

        transactionTemplate.executeWithoutResult(action -> {
            orderDao.update(order);
            orderAndProductService.delete(order);
            orderAndProductService.save(order);
        });
    }

    public void deleteOrder(Integer id) {
        if (Util.checkNull(id))
            throw new IllegalArgumentException("Can't delete null order id");

        orderDao.delete(id);
    }

}









