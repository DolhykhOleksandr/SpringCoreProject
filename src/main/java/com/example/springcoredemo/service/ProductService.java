package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("Product id cannot be null for update");
        }

        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productDTO.getId()));

        existingProduct.setName(productDTO.getName());
        existingProduct.setCost(productDTO.getCost());


        productRepository.save(existingProduct);
    }


    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}