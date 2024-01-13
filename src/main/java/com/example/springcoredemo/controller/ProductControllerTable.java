package com.example.springcoredemo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RequestMapping("/api/v1/table")
@RestController
public class ProductControllerTable {

    private final ResourceLoader resourceLoader;

    @CrossOrigin("*")
    @GetMapping()
    public @ResponseBody String getEmployeesTable() throws IOException {
        return new String(resourceLoader.getResource("classpath:/static/table.html")
                .getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    @CrossOrigin("*")
    @GetMapping("/save")
    public @ResponseBody String save() throws IOException {
        return new String(resourceLoader.getResource("classpath:/static/save.html")
                .getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}