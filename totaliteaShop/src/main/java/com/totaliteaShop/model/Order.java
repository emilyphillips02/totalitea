package com.totaliteaShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private Double totalPrice;

    @Column(name = "shipping_cost", nullable = false, precision = 10, scale = 2)
    private Double shippingCost;

    @Column(nullable = false, length = 20)
    private String status = "PENDING";

    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}