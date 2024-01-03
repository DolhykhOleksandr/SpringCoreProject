package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.OrderConverter;
import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public OrderDTO get(Integer id) {
        return orderRepository.findById(id)
                .map(this::getOrderDTOWithProducts)
                .orElseThrow();
    }

    private OrderDTO getOrderDTOWithProducts(Order order) {
        OrderDTO orderDTO = OrderConverter.orderToOrderDTO(order);
        orderDTO.setProductDTOS(order.getProducts()
                .stream()
                .map(productRef -> productService.get(productRef.getProductId()))
                .toList());
        return orderDTO;
    }

    public List<OrderDTO> getAll() {
        List<OrderDTO> orderEntities = new ArrayList<>();
        orderRepository.findAll()
                .forEach(order -> orderEntities.add(getOrderDTOWithProducts(order)));
        return orderEntities;
    }

    public void save(OrderDTO orderDTO) {
        Order order = OrderConverter.orderDTOToOrder(orderDTO);
        double cost = 0.0;
        for (ProductDTO productDTO : orderDTO.getProductDTOS()) {
            if (productDTO.getId() != null) {
                cost += productService.get(productDTO.getId()).getCost();
                order.addProduct(ProductConverter.productDTOToProduct(productDTO));
            }
        }
        order.setCost(cost);
        orderRepository.save(order);
    }

    public void update(OrderDTO orderDTO) {
        save(orderDTO);
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

}