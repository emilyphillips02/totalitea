package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name= "total_price")
    private Double totalPrice;

    @Column (name = "shipping_cost")
    private Double shippingCost;

    private String status ="PENDING";

    @Column (name = "order_date", insertable = false, updatable = false)
    private LocalDateTime orderDate;

}
