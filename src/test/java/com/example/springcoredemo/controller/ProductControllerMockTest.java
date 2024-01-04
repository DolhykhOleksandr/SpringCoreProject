package com.example.springcoredemo.controller;



import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.ProductRepository;
import com.example.springcoredemo.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Disabled
class ProductControllerMockTest {

    // TODO add feature for testing ProductController with login and password
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = List.of(
                Product.builder().name("Burger").cost(50.0).build(),
                Product.builder().name("Big mac").cost(70.0).build()
        );
    }

    @Test
    void getById() {
        Product productExpected = productRepository.save(products.get(0));

        ProductDTO productDTOActual = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/products/" + productExpected.getProductId(),
                ProductDTO.class);

        Assertions.assertEquals(ProductConverter.productToProductDTO(productExpected), productDTOActual);
    }

    @Test
    void getAll() {
        ProductDTO[] expectedProductDTOS = new ProductDTO[products.size()];
        for (int i = 0; i < products.size(); i++) {
            expectedProductDTOS[i] = ProductConverter.productToProductDTO(productRepository.save(products.get(i)));
        }

        ProductDTO[] productDTOSActual = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/products",
                ProductDTO[].class
        );

        for (int i = 0; i < products.size(); i++)
            Assertions.assertEquals(expectedProductDTOS[i], productDTOSActual[i]);
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(ProductConverter.productToProductDTO(products.get(0)))))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update() throws Exception {
        ProductDTO productDTO = ProductConverter.productToProductDTO(
                productRepository.save(products.get(0)));
        productDTO.setName("Potato free");

        mockMvc.perform(put("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(productDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = productRepository.save(products.get(0));

        mockMvc.perform(delete("/api/v1/products/" + product.getProductId()))
                .andExpect(status().is2xxSuccessful());
    }
}