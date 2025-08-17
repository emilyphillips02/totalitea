package com.totaliteaShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "shipping_rules")
public class ShippingRuleModel {
    @Id
    @ColumnDefault("nextval('shipping_rules_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "min_weight", nullable = false, precision = 6, scale = 2)
    private BigDecimal minWeight;

    @Column(name = "max_weight", precision = 6, scale = 2)
    private BigDecimal maxWeight;

    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "free_shipping_threshold", precision = 10, scale = 2)
    private BigDecimal freeShippingThreshold;

}