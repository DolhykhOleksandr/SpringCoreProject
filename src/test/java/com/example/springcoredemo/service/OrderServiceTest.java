package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.OrderConverter;
import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Order;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.OrderDTO;
import com.example.springcoredemo.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private OrderService orderService;
    private Order order;
    private Integer orderId;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productService);
        orderId = 1;
        products = List.of(
                Product.builder().productId(1).name("Burger").cost(30.00).build(),
                Product.builder().productId(2).name("Big Mac").cost(90.00).build()
        );
        order = Order.builder().orderId(orderId).date(LocalDate.now()).cost(120.00).products(new HashSet<>()).build();
        for (Product product : products) {
            order.addProduct(product);
        }
    }

    @Test
    public void get() {

        OrderDTO orderDTOExpected = orderToOrderDTO(order);
        when(orderRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(order));
        setMockForEachProduct(products);


        OrderDTO orderDTOActual = orderService.get(orderId);


        Assertions.assertEquals(orderDTOExpected, orderDTOActual);
        assertThrows(NoSuchElementException.class, () -> {
            orderService.get(null);
        });
    }

    private OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTOExpected = OrderConverter.orderToOrderDTO(order);
        orderDTOExpected.setProductDTOS(products.stream()
                .map(ProductConverter::productToProductDTO)
                .toList());
        return orderDTOExpected;
    }

    private void setMockForEachProduct(List<Product> products) {
        for (Product product : products)
            when(productService.get(Mockito.eq(product.getProductId())))
                    .thenReturn(ProductConverter.productToProductDTO(product));
    }

    @Test
    public void getAll() {

        List<Order> orders = List.of(order);
        List<OrderDTO> orderDTOSExpected = orders.stream()
                .map(this::orderToOrderDTO)
                .toList();
        when(orderRepository.findAll()).thenReturn(orders);
        for (int i = 0; i < orders.size(); i++) {
            setMockForEachProduct(products);
        }


        List<OrderDTO> orderDTOSActual = orderService.getAll();


        Assertions.assertEquals(orderDTOSExpected, orderDTOSActual);
    }

    @Test
    public void save() {

        setMockForEachProduct(products);


        orderService.save(orderToOrderDTO(order));


        Order actualOrder = getCapturedOrder();
        Assertions.assertEquals(order, actualOrder);
        Assertions.assertEquals(order.getCost(), actualOrder.getCost());
    }

    private Order getCapturedOrder() {
        ArgumentCaptor<Order> orderArgumentCaptor =
                ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());

        return orderArgumentCaptor.getValue();
    }

    @Test
    public void update() {

        when(orderRepository.save(order)).thenReturn(order);
        products.get(0).setCost(100.00);
        order.setCost(190.00);
        setMockForEachProduct(products);


        orderService.update(orderToOrderDTO(order));


        Assertions.assertEquals(order, getCapturedOrder());
    }

    @Test
    public void delete() {

        doNothing().when(orderRepository).deleteById(orderId);


        orderService.delete(orderId);


        verify(orderRepository, times(1)).deleteById(orderId);
    }

}