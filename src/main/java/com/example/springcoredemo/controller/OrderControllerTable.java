package com.example.springcoredemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RequestMapping("/api/v1/table/orders")
@RestController
public class OrderControllerTable {

    private final ResourceLoader resourceLoader;

    @CrossOrigin("*")
    @GetMapping()
    public @ResponseBody String getEmployeesTable() throws IOException {
        return new String(resourceLoader.getResource("classpath:/static/order-table.html")
                .getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}