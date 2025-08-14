package com.totaliteaShop.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String supplier;

    @Column(nullable = false, length = 50)
    private String type;

    // Remove precision and scale for Double fields
    @Column(name = "weight_grams", nullable = false)
    private Double weightGrams;

    @Column(name = "price_gbp", nullable = false)
    private Double priceGbp;

    @Column(name = "caffeine_content_mg_per_g")
    private Double caffeineContentMgPerG;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
