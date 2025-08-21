package com.totaliteaShop.respository;

import com.totaliteaShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndType(String category, String type);
    List<Product> findBySupplier(String supplier);
    List<Product> findByCategoryAndTypeAndSupplier(String category, String type, String supplier);
}
