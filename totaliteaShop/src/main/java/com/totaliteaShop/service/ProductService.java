package com.totaliteaShop.service;

import com.totaliteaShop.model.Product;
import com.totaliteaShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository .findById(id);
    }
    public List<Product>getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public List<Product>getProductsBySupplier(String supplier) {
        return productRepository.findBySupplier(supplier);
    }
    public List<Product>getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Product>getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
