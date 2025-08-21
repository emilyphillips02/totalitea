package com.totaliteaShop.dataseed;

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
    public void run(String... args) {
        if (productService.getAllProducts().isEmpty()) {
            List<Product> products = new ArrayList<>();


            String[] looseLeafTeas = {"Earl Grey", "Green Tea", "Oolong", "Jasmine", "English Breakfast"};
            for (String name : looseLeafTeas) {
                products.add(createProduct(name + " Tea", "Totalitea", "Loose Leaf",
                        randomWeight(150, 300), randomPrice(3.5, 8.0), randomCaffeine(2, 5), "Tea"));
            }


            String[] baggedTeas = {"Chamomile", "Peppermint", "Lemongrass", "Hibiscus"};
            for (String name : baggedTeas) {
                products.add(createProduct(name + " Tea", "Herbal Blends Co.", "Bagged",
                        randomWeight(50, 150), randomPrice(2.5, 6.0), 0, "Tea"));
            }


            String[] groundCoffees = {"Espresso", "Latte Blend", "Americano Roast", "Cappuccino Roast"};
            for (String name : groundCoffees) {
                products.add(createProduct(name + " Coffee", "Global Roasters", "Ground",
                        randomWeight(250, 500), randomPrice(5.0, 12.0), randomCaffeine(8, 15), "Coffee"));
            }


            String[] beanCoffees = {"Colombian Beans", "Ethiopian Yirgacheffe", "Sumatra Dark Roast"};
            for (String name : beanCoffees) {
                products.add(createProduct(name, "Coffee Masters", "Whole Beans",
                        randomWeight(200, 500), randomPrice(7.0, 15.0), randomCaffeine(10, 18), "Coffee"));
            }


            String[] herbalInfusions = {"Rooibos", "Ginger & Lemon", "Mint Blend"};
            for (String name : herbalInfusions) {
                products.add(createProduct(name + " Infusion", "Nature's Cup", "Bagged",
                        randomWeight(80, 200), randomPrice(3.0, 6.0), 0, "Herbal"));
            }

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
