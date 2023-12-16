package com.example.springcoredemo.controller;


import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.service.ProductService;
import com.example.springcoredemo.utils.Util;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public void save(@RequestBody ProductDTO productDTO) {
        if (Util.anyNull(productDTO.getName(), productDTO.getCost()))
            throw new IllegalArgumentException("Can't save null product");
        productService.save(productDTO);
    }

    @PutMapping
    public void update(@RequestBody ProductDTO productDTO) {
        if (Util.anyNull(productDTO.getId()))
            throw new IllegalArgumentException("Can't update null product");
        productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

}