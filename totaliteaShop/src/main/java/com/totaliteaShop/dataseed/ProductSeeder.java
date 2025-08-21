package com.totaliteaShop.config;

import com.totaliteaShop.model.Product;
import com.totaliteaShop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductService productService;
    private final Random random = new Random();

    public ProductSeeder(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productService.getAllProducts().isEmpty()) {
            List<Product> products = new ArrayList<>();

            // Tea category
            String[] teaNames = {"Earl Grey", "Green Tea", "Oolong", "Chamomile", "Peppermint", "Jasmine", "English Breakfast"};
            for (String name : teaNames) {
                products.add(createProduct(name + " Tea", "Totalitea", "Loose Leaf", randomWeight(150, 300),
                        randomPrice(3.5, 8.0), randomCaffeine(0, 5), "Tea"));
            }

            // Coffee category
            String[] coffeeNames = {"Espresso", "Latte", "Americano", "Cappuccino", "Mocha", "Flat White"};
            for (String name : coffeeNames) {
                products.add(createProduct(name + " Coffee", "Totalitea", "Ground", randomWeight(250, 500),
                        randomPrice(5.0, 10.0), randomCaffeine(8, 15), "Coffee"));
            }

            // Herbal category
            String[] herbalNames = {"Chamomile", "Peppermint", "Rooibos", "Lemongrass", "Hibiscus"};
            for (String name : herbalNames) {
                products.add(createProduct(name + " Tea", "Totalitea", "Bagged", randomWeight(100, 200),
                        randomPrice(3.0, 5.0), 0, "Herbal"));
            }

            // Save all products
            products.forEach(productService::saveProduct);
        }
    }

    private Product createProduct(String name, String supplier, String type,
                                  int weightGrams, double priceGbp, double caffeine, String category) {
        Product product = new Product();
        product.setName(name);
        product.setSupplier(supplier);
        product.setType(type);
        product.setWeightGrams(BigDecimal.valueOf(weightGrams));
        product.setPriceGbp(BigDecimal.valueOf(priceGbp));
        product.setCaffeineContentMgPerG(BigDecimal.valueOf(caffeine));
        product.setCategory(category);
        return product;
    }

    private int randomWeight(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private double randomPrice(double min, double max) {
        return Math.round((min + (max - min) * random.nextDouble()) * 100.0) / 100.0;
    }

    private double randomCaffeine(double min, double max) {
        return Math.round((min + (max - min) * random.nextDouble()) * 10.0) / 10.0;
    }
}