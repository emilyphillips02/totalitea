package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="order_items")
@Getter
@Setter

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name= "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Integer quantity;

    @Column (name ="sub_total", nullable = false, precision=10, scale=2)
    private BigDecimal subTotal;
}
