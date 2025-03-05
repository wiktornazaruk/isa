package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllWithWarehouse();
    }

    public List<Product> findProductsByWarehouse(String warehouseId) {
        return productRepository.findByWarehouse_Id(warehouseId);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductsInPriceRange(double lowBound, double highBound) {
        return productRepository.findProductsByPriceRange(lowBound, highBound);
    }
}