package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false, length = 100)
    private String supplier;

    @Column(nullable = false, length = 50)
    private String type;

    @Column (name = "weight_grams", nullable = false, precision = 6, scale = 2)
    private Double weightGrams;

    @Column(name = "price_gbp", nullable = false, precision = 10, scale = 2)
    private Double priceGbp;

    @Column(name ="caffeine_content_mg_per_g", precision =5, scale =2)
    private Double caffeineContentMgPerG;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT _TIMESTAMP")
    private LocalDateTime createdAt;

}
