package com.example.springcoredemo.repository;

import com.example.springcoredemo.model.Order;
import com.example.springcoredemo.model.Product;
import com.example.springcoredemo.repository.dao.OrderDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Repository("orderRepoMem")
public class OrderRepositoryMemory implements OrderDao {

    private final List<Product> products1 = List.of(
            new Product(1, "Burger", 100),
            new Product(2, "Potato Free", 80.5),
            new Product(3, "Ice Cream", 60));
    private final List<Product> products2 = List.of(
            new Product(4, "Coffee", 40),
            new Product(5, "Cake", 40)
    );

    private List<Order> orders = List.of(
            new Order(1, LocalDate.now(), 240.5, products1),
            new Order(2, LocalDate.now(), 80.0, products2));

    @Override
    public Optional<Order> getById(int id) {
        return orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Integer save(Order order) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        Order newOrder = new Order(randomNum, order.getDate(), order.getCost(), new ArrayList<>(order.getProducts()));
        orders.add(newOrder);
        return randomNum;
    }

    @Override
    public void update(Order order) {
        if (order == null) return;
        for (Order tempOrder : orders) {
            int index = orders.indexOf(tempOrder);
            if (index >= 0) {
                orders.set(index, new Order(tempOrder.getId(), order.getDate(), order.getCost(), order.getProducts()));
                return;
            }
        }


    }

    @Override
    public void delete(Integer id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }
}
