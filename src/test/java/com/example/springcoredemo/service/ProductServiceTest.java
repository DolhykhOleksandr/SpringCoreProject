package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    private Product product;
    private Integer productId;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
        productId = 1;
        product = Product.builder()
                .productId(productId)
                .name("Burger")
                .cost(50.0)
                .orders(new ArrayList<>())
                .build();
    }

    @Test
    public void get() {
        // given
        ProductDTO productDTOExpected = ProductConverter.productToProductDTO(product);
        when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(product));

        // when
        ProductDTO productDTOActual = productService.get(productId);

        // then
        assertEquals(productDTOExpected, productDTOActual);
        assertThrows(NoSuchElementException.class, () -> {
            productService.get(null);
        });
    }

    @Test
    public void getAll() {
        // given
        List<Product> products = List.of(product);
        List<ProductDTO> productDTOSExpected = products
                .stream()
                .map(ProductConverter::productToProductDTO)
                .toList();
        when(productRepository.findAll()).thenReturn(products);

        // when
        List<ProductDTO> productDTOSActual = productService.getAll();

        // then
        assertEquals(productDTOSExpected, productDTOSActual);
    }
    @Test
    void save() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TestProduct");
        productDTO.setCost(100.0);

        Product savedProductEntity = new Product();
        savedProductEntity.setProductId(1);

        when(productRepository.save(any())).thenReturn(savedProductEntity);

        productService.save(productDTO);

        assertNotNull(productDTO.getId());
        assertEquals(savedProductEntity.getProductId(), productDTO.getId());

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());

        Product capturedProduct = productCaptor.getValue();
        assertEquals(productDTO.getName(), capturedProduct.getName());
        assertEquals(productDTO.getCost(), capturedProduct.getCost());
    }

    @Test
    void update() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("UpdatedProduct");
        productDTO.setCost(150.0);

        Product existingProductEntity = new Product();
        existingProductEntity.setProductId(1);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setProductId(1);
            return savedProduct;
        });

        productService.update(productDTO);

        assertNotNull(productDTO.getId());
        assertEquals(1, productDTO.getId().intValue());

        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void delete() {
        // given - precondition or setup
        doNothing().when(productRepository).deleteById(productId);

        // when -  action or the behaviour that we are going test
        productService.delete(productId);

        // then - verify the output
        verify(productRepository, times(1)).deleteById(productId);
    }

}