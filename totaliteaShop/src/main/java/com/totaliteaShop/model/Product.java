package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String supplier;

    @Column(name="weight_grams", nullable = false, precision = 6, scale = 2)
    private BigDecimal weightGrams;

    @Column(name="price_gbp", nullable = false,precision = 10, scale = 2)
    private BigDecimal priceGbps;

    @Column(name = "caffeine_content_mg_per_g", precision = 5, scale = 2)
    private BigDecimal caffeineContentMgPerG;

    @Column(nullable = false)
    private String category;

    @Column(name="created_at")
    private LocalDateTime createdAt =LocalDateTime.now();

    }

