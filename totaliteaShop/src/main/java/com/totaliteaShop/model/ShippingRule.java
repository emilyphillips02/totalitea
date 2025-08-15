package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "shipping_rules")
@Getter
@Setter
public class ShippingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_weight", nullable = false, precision = 6, scale = 2)
    private BigDecimal minWeight;

    @Column(name = "max_weight", precision = 6, scale = 2)
    private BigDecimal maxWeight;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "free_shipping_threshold", precision = 10, scale = 2)
    private BigDecimal freeShippingThreshold;
}
