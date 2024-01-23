package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.OrderConverter;
import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.OrderRepository;
import com.example.springcoredemo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
                .map(product -> productRepository.findById(product.getProductId()))
                .map(product -> ProductConverter.productToProductDTO(product.orElseThrow()))
                .toList());
        return orderDTO;
    }

    public List<OrderDTO> getAll() {
        List<OrderDTO> orderEntities = new ArrayList<>();
        orderRepository.findAll()
                .forEach(order -> orderEntities.add(getOrderDTOWithProducts(order)));
        return orderEntities;
    }

    public OrderDTO save(OrderDTO orderDTO) {

        List<ProductDTO> productDTOs = orderDTO.getProductDTOS();

        if (productDTOs == null || productDTOs.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one product.");
        }

        Order order = OrderConverter.orderDTOToOrder(orderDTO);

        List<Product> products = orderDTO.getProductDTOS()
                .stream()
                .filter(productDTO -> productDTO.getId() != null)
                .map(productDTO -> productRepository.findById(productDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productDTO.getId())))
                .toList();

        order.setCost(calculateAndGetCost(products));
        order.setProducts(products);


        Order savedOrder = orderRepository.save(order);


        return getOrderDTOWithProducts(savedOrder);
    }


    private double calculateAndGetCost(List<Product> products) {
        double cost = 0.0;
        for (Product product : products) {
            if (product.getProductId() != null)
                cost += productRepository.findById(product.getProductId())
                        .orElseThrow()
                        .getCost();
        }
        return cost;
    }

    public OrderDTO update(OrderDTO orderDTO) {
        if (orderDTO.getId() == null) {
            throw new IllegalArgumentException("Order id cannot be null for update");
        }

        Order existingOrder = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderDTO.getId()));

        existingOrder.setDate(orderDTO.getDate());

        List<Product> existingProducts = existingOrder.getProducts();


        for (ProductDTO productDTO : orderDTO.getProductDTOS()) {
            if (productDTO.getId() != null) {

                Optional<Product> existingProduct = existingProducts.stream()
                        .filter(p -> p.getProductId().equals(productDTO.getId()))
                        .findFirst();

                if (existingProduct.isPresent()) {

                    Product productToUpdate = existingProduct.get();
                    productToUpdate.setName(productDTO.getName());
                    productToUpdate.setCost(productDTO.getCost());
                } else {

                    Product newProduct = productRepository.findById(productDTO.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productDTO.getId()));
                    existingProducts.add(newProduct);
                }
            }
        }


        existingOrder.setCost(calculateAndGetCost(existingProducts));


        Order updatedOrder = orderRepository.save(existingOrder);


        return getOrderDTOWithProducts(updatedOrder);
    }


    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

}