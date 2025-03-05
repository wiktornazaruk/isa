package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findInPriceRange(double lowBound, double highBound) {
        return productRepository.findAll().stream()
                .filter(p -> p.getPrice() >= lowBound && p.getPrice() <= highBound)
                .toList();
    }

    public boolean productNameIsTaken(String name) {
        return productRepository.findByName(name).isPresent();
    }

    public void removeWarehouseProducts(String warehouseName) {
        List<Product> productsInWarehouse = productRepository.findAll().stream()
                .filter(product -> warehouseName.equals(product.getWarehouseName()))
                .toList();
        productsInWarehouse.forEach(product -> productRepository.deleteById(product.getId()));
    }
}