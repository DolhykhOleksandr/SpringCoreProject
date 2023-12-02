package com.example.springcoredemo.service;


import com.example.springcoredemo.dto.OrderDto;
import com.example.springcoredemo.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static List<OrderDto> orders = new ArrayList<>();

    static {
        orders.add(OrderDto.builder()
                .id(1)
                .date(LocalDate.of(2021, 3, 11))
                .cost(0.0)
                .products(new ArrayList<>())
                .build());
        orders.add(OrderDto.builder()
                .id(2)
                .date(LocalDate.of(2022, 10, 15))
                .cost(0.0)
                .products(new ArrayList<>())
                .build());
        orders.add(OrderDto.builder()
                .id(3)
                .date(LocalDate.of(2023, 6, 14))
                .cost(0.0)
                .products(new ArrayList<>())
                .build());
    }

    public List<OrderDto> getAll() {
        return orders;
    }

    public OrderDto getById(int id) {
        OrderDto result = null;
        for (OrderDto order : orders) {
            if (order.getId() == id) {
                result = order;
            }
        }
        return result;
    }

    public OrderDto addOrder(OrderDto order) {
        int index = orders.size() + 1;
        order.setId(index);
        double sum = calculateCost(order);
        order.setCost(sum);
        orders.add(order);
        return order;
    }

    public OrderDto removeOrder(int id) {
        OrderDto order = getById(id);
        orders.remove(order);
        return order;
    }

    public OrderDto updateOrder(OrderDto updatedOrder) {
        for (OrderDto order : orders) {
            if (order.getId() == updatedOrder.getId()) {
                order.setDate(updatedOrder.getDate());
                order.setCost(calculateCost(updatedOrder));
                order.setProducts(updatedOrder.getProducts());
            }
        }
        return updatedOrder;
    }

    public List<ProductDto> getAllByOrderId(int orderId) {
        OrderDto order = getById(orderId);
        return order.getProducts();
    }

    public ProductDto addProduct(int orderId, ProductDto product) {
        OrderDto order = getById(orderId);
        order.setCost(order.getCost() + product.getCost());
        List<ProductDto> products = order.getProducts();
        int productIndex = products.size() + 1;
        product.setId(productIndex);
        products.add(product);
        order.setProducts(products);
        return product;
    }

    public ProductDto removeProduct(int orderID, int productId) {
        ProductDto removedProduct = null;
        OrderDto order = getById(orderID);
        List<ProductDto> products = order.getProducts();
        for (ProductDto product : products) {
            if (product.getId() == productId) {
                removedProduct = product;
                order.setCost(order.getCost() - product.getCost());
                products.remove(product);
            }
        }
        return removedProduct;
    }

    public ProductDto updateProduct(int orderId, ProductDto newProduct) {
        OrderDto order = getById(orderId);
        List<ProductDto> products = order.getProducts();
        for (ProductDto oldProduct : products) {
            if (oldProduct.getId() == newProduct.getId()) {
                order.setCost(order.getCost() - oldProduct.getCost() + newProduct.getCost());
                oldProduct.setName(newProduct.getName());
                oldProduct.setCost(newProduct.getCost());
            }
        }
        return newProduct;
    }

    public double calculateCost(OrderDto order) {
        List<ProductDto> products = order.getProducts();
        double sum = 0.0;
        if (!products.isEmpty()) {
            for (ProductDto product : products) {
                sum += product.getCost();
            }
        }
        return sum;
    }

}