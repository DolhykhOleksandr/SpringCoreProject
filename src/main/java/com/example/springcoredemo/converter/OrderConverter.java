package com.example.springcoredemo.converter;




import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.model.OrderDTO;

import java.util.ArrayList;


public class OrderConverter {

    public static OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getOrderId())
                .date(order.getDate())
                .cost(order.getCost())
                .build();
    }

    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getId())
                .date(orderDTO.getDate())
                .cost(orderDTO.getCost())
                .products(new ArrayList<>())
                .build();
    }

}