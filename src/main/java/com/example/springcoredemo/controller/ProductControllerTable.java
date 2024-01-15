package com.example.springcoredemo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RequestMapping("/api/v1/table/products")
@RestController
public class ProductControllerTable {

    private final ResourceLoader resourceLoader;

    @CrossOrigin("*")
    @GetMapping()
    public @ResponseBody String getEmployeesTable() throws IOException {
        return new String(resourceLoader.getResource("classpath:/static/product-table.html")
                .getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    @CrossOrigin("*")
    @GetMapping("/save")
    public @ResponseBody String save() throws IOException {
        return new String(resourceLoader.getResource("classpath:/static/save-product.html")
                .getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}