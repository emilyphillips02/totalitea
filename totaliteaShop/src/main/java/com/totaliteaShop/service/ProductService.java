package com.totaliteaShop.service;

import com.totaliteaShop.model.Product;
import com.totaliteaShop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByCategoryAndType(String category, String type) {
        return productRepository.findByCategoryAndType(category, type);
    }

    public List<Product> getProductsBySupplier(String supplier) {
        return productRepository.findBySupplier(supplier);
    }

    public List<Product> getProductsByCategoryAndTypeAndSupplier(String category, String type, String supplier) {
        return productRepository.findByCategoryAndTypeAndSupplier(category, type, supplier);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //  Added this so BasketController works
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}