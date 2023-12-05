package com.example.springcoredemo.service;

import com.example.springcoredemo.model.Product;
import com.example.springcoredemo.repository.dao.ProductDao;
import com.example.springcoredemo.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Optional<Product> getProductById(Integer id) {
        return productDao.getById(id);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void saveProduct(Product product) {
        if (Util.checkNull(product, product.getName(), product.getCost()))
            throw new IllegalArgumentException("Can't save null product");
        productDao.save(product);
    }

    public void updateProduct(Product product) {
        if (Util.checkNull(product, product.getName(), product.getCost(), product.getId()))
            throw new IllegalArgumentException("Can't update null product");
        productDao.update(product);
    }

    public void deleteProduct(Integer id) {
        if (Util.checkNull(id))
            throw new IllegalArgumentException("Can't delete null product");
        productDao.delete(id);
    }

}
