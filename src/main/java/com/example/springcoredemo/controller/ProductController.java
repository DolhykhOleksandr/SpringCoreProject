package com.example.springcoredemo.controller;

import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.repository.ProductRepository;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.service.ProductService;
import com.example.springcoredemo.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Integer id) {
        return productService.get(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {

        if (Util.anyNull(productDTO.getName(), productDTO.getCost())) {
            throw new IllegalArgumentException("Can't save null product");
        }

        productService.save(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductDTO productDTO) {

        if (Util.anyNull(productDTO.getId())) {
            throw new IllegalArgumentException("Product id cannot be null for update");
        }
        if (Util.anyNull(productDTO.getName(), productDTO.getCost())) {
            throw new IllegalArgumentException("Can't update product with null values");
        }

        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElse(null);

        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + productDTO.getId());
        }

        existingProduct.setName(productDTO.getName());
        existingProduct.setCost(productDTO.getCost());

        productService.update(productDTO);

        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
        productService.delete(id);

        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");

    }
}
