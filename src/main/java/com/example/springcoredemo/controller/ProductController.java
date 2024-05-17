package com.example.springcoredemo.controller;

import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.repository.ProductRepository;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.service.ProductService;
import com.example.springcoredemo.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Product")
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @ApiOperation("Get product by ID")
    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Integer id) {
        return productService.get(id);
    }

    @ApiOperation("Get all products")
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @ApiOperation("Create a new product")
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {

        if (Util.anyNull(productDTO.getName(), productDTO.getCost())) {
            throw new IllegalArgumentException("Can't save null product");
        }

        productService.save(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @ApiOperation("Update an existing product")
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

    @ApiOperation("Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
        productService.delete(id);

        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");

    }
}
