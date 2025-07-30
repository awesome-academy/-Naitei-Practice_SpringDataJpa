package org.ledang.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@ToString(exclude = "user")
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Order(double price) {
        this.price = price;
    }
}
