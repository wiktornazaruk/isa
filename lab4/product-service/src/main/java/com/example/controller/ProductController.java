package com.example.controller;

import com.example.model.Product;
import com.example.model.WarehouseInfo;
import com.example.dto.ProductCreateOrUpdateDTO;
import com.example.dto.ProductReadCollectionDTO;
import com.example.dto.ProductReadDTO;
import com.example.service.ProductService;
import com.example.service.WarehouseInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final WarehouseInfoService warehouseInfoService;

    public ProductController(ProductService productService, WarehouseInfoService warehouseInfoService) {
        this.productService = productService;
        this.warehouseInfoService = warehouseInfoService;
    }

    @PutMapping
    public ResponseEntity<ProductReadDTO> createProduct(@RequestBody ProductCreateOrUpdateDTO p) {
        if (productService.productNameIsTaken(p.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product name is taken"
            );

        }
        if (p.getPrice() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product price must be grater or equal than 0"
            );
        }
        if (p.getQuantity() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product quantity must be grater or equal than 0"
            );
        }
        Product product;
        if(!warehouseInfoService.warehouseExists(p.getWarehouseName())){
             product = new Product(
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity(),
                    p.getCategory()
            );
        } else {
            product = new Product(
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity(),
                    p.getCategory(),
                    p.getWarehouseName()
            );
        }

        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ProductReadDTO(
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getQuantity(),
                        savedProduct.getCategory(),
                        savedProduct.getWarehouseName()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductReadCollectionDTO>> getAllProducts() {
        List<ProductReadCollectionDTO> products = productService.findAll().stream()
                .map(p -> new ProductReadCollectionDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return ResponseEntity.ok(new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory(),
                product.getWarehouseName()
        ));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<ProductReadDTO> updateProduct(@PathVariable String name, @RequestBody Product p) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        if (p.getName() != null) {
            String newName = p.getName();
            if (!newName.equals(name)) {
                if (productService.productNameIsTaken(newName)) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Product name is taken"
                    );
                }
                else {
                    product.setName(newName);
                }
            }
        }
        double price = p.getPrice();
        if (price >= 0) {
            product.setPrice(price);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product price must be grater or equal than 0"
            );
        }
        int quantity = p.getQuantity();
        if (quantity >= 0) {
            product.setQuantity(quantity);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product quantity must be grater or equal than 0"
            );
        }
        String category = p.getCategory();
        if (category != null) {
            product.setCategory(category);
        }
        String warehouseName = p.getWarehouseName();
        if (warehouseName != null) {
            product.setWarehouseName(warehouseName);
        }
        Product updatedProduct = productService.save(product);
        return ResponseEntity.ok(new ProductReadDTO(
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getQuantity(),
                updatedProduct.getCategory(),
                updatedProduct.getWarehouseName()
        ));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productService.deleteById(product.getId());
        return ResponseEntity.noContent().build();
    }
}
