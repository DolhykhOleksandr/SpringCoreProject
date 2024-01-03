package com.example.springcoredemo.converter;


import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;

public class ProductConverter {

    public static ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getProductId())
                .name(product.getName())
                .cost(product.getCost())
                .build();
    }

    public static Product productDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .productId(productDTO.getId())
                .name(productDTO.getName())
                .cost(productDTO.getCost())
                .build();
    }
}