package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.ProductConverter;
import com.example.springcoredemo.entity.Product;
import com.example.springcoredemo.model.ProductDTO;
import com.example.springcoredemo.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    @Mock
    private Product product;
    private Integer productId;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
        productId = 1;
        product = Product.builder().productId(productId).name("Burger").cost(50.0).build();
    }

    @Test
    public void get() {
        // given
        ProductDTO productDTOExpected = ProductConverter.productToProductDTO(product);
        when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(product));

        // when
        ProductDTO productDTOActual = productService.get(productId);

        // then
        Assertions.assertEquals(productDTOExpected, productDTOActual);
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
        Assertions.assertEquals(productDTOSExpected, productDTOSActual);
    }

    @Test
    public void save() {
        // given
        // when
        productService.save(ProductConverter.productToProductDTO(product));

        // then
        Assertions.assertEquals(product, getCapturedProduct());
    }

    @Test
    public void update() {
        // given
        when(productRepository.save(product)).thenReturn(product);
        product.setName("Big Mac");
        product.setCost(140.00);

        // when
        productService.update(ProductConverter.productToProductDTO(product));

        // then
        Assertions.assertEquals(product, getCapturedProduct());
    }

    private Product getCapturedProduct() {
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());

        return productArgumentCaptor.getValue();
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