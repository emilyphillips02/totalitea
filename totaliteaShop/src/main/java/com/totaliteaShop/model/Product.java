package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "totalitea")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "supplier", nullable = false, length = 100)
    private String supplier;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "weight_grams", nullable = false, precision = 6, scale = 2)
    private BigDecimal weightGrams;

    @Column(name = "price_gbp", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceGbp;

    @Column(name = "caffeine_content_mg_per_g", precision = 5, scale = 2)
    private BigDecimal caffeineContentMgPerG;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}