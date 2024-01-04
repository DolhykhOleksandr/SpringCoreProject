package com.example.springcoredemo.controller;


import com.example.springcoredemo.converter.OrderConverter;
import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.repository.OrderRepository;
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

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Disabled
class OrderControllerMockTest {

    // TODO add feature for testing OrderController with login and password

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private List<Product> products;
    private Order order;
    private OrderDTO orderExpected;

    @BeforeEach
    void setUp() {
        products = List.of(
                Product.builder().name("Burger").cost(30.00).build(),
                Product.builder().name("Big Mac").cost(90.00).build()
        );
        order = saveOrder(Order.builder()
                .date(LocalDate.now())
                .cost(120.00)
                .products(new ArrayList<>())
                .build());
        orderExpected = orderToOrderDTO(order);
    }

    @Test
    void get() {
        OrderDTO orderDTOActual = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/orders/" + orderExpected.getId(),
                OrderDTO.class);

        Assertions.assertEquals(orderExpected, orderDTOActual);
    }

    @Test
    void getAll() {
        OrderDTO[] orderDTOS = new OrderDTO[]{orderExpected};

        OrderDTO[] orderDTOSActual = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/orders",
                OrderDTO[].class);

        for (int i = 0; i < orderDTOS.length; i++)
            Assertions.assertEquals(orderDTOS[i], orderDTOSActual[i]);
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(orderExpected)))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    void update() throws Exception {
        order.setDate(LocalDate.of(2024, 1, 4));
        OrderDTO orderExpected = orderToOrderDTO(order);

        mockMvc.perform(put("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(orderExpected)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/api/v1/orders/" + order.getOrderId()))
                .andExpect(status().is2xxSuccessful());
    }

    private OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTOExpected = OrderConverter.orderToOrderDTO(order);
        orderDTOExpected.setProductDTOS(products.stream()
                .map(ProductConverter::productToProductDTO)
                .toList());
        return orderDTOExpected;
    }

    private List<Product> saveProducts(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            Product savedProduct = productRepository.save(product);
            productList.add(savedProduct);
        }
        return productList;
    }

    private Order saveOrder(Order order) {
        List<Product> productList = saveProducts(products);
        order.setProducts(productList);
        return orderRepository.save(order);
    }
}