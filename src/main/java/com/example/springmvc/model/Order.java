package com.example.springmvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

private String productName;
private Double price;

// Связь Many-to-One: много заказов → один пользователь
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id") //Внешний ключ в таблице orders
    private User user;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
