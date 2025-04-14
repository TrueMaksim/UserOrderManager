package com.example.springmvc.repository;

import com.example.springmvc.model.Order;
import com.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
