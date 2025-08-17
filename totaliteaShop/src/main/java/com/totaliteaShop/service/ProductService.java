package com.totaliteaShop.service;

import com.totaliteaShop.model.ProductModel;
import com.totaliteaShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductModel createProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }
    public Optional<ProductModel> getProductById(Long id) {
        return productRepository .findById(id);
    }
    public List<ProductModel>getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public List<ProductModel>getProductsBySupplier(String supplier) {
        return productRepository.findBySupplier(supplier);
    }
    public List<ProductModel>getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    public List<ProductModel>getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
