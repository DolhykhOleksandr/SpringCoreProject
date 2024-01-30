package com.example.springcoredemo.service;

import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.OrderRepository;
import com.example.springcoredemo.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public ProductDTO get(Integer id) {
        return ProductConverter.productToProductDTO(productRepository.findById(id).orElseThrow());
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(product -> products.add(ProductConverter.productToProductDTO(product)));
        return products;
    }

    public void save(ProductDTO productDTO) {
        Product productEntity = ProductConverter.productDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(productEntity);
        productDTO.setId(savedProduct.getProductId());
    }

    public void update(ProductDTO productDTO) {
        save(productDTO);
    }

    public void delete(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        optionalProduct.ifPresent(product -> {
            for (Order order : product.getOrders()) {
                order.getProducts().remove(product);
                if (order.getProducts().isEmpty()) {
                    orderRepository.delete(order);
                }
            }
            productRepository.delete(product);
        });
    }
}


