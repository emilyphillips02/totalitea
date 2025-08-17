package com.totaliteaShop.repository;

import com.totaliteaShop.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByCategory(String category);
    List<ProductModel> findBySupplier(String supplier);
    List<ProductModel> findByNameContainingIgnoreCase(String name);
}
