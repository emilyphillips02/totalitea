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
    private String productName;
    private String supplier;
    private String type;

    @Column (name = "weight_grams")
    private Double weightGrams;

    @Column(name = "price_gbp")
    private Double priceGbp;

    @Column(name ="caffeine_content_mg_per_g")
    private Double caffeineContentMgPerG;

    private String category;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}
