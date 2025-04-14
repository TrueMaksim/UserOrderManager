package com.example.springmvc.model;

import com.example.springmvc.repository.OrderRepository;
import com.example.springmvc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // Используйте правильный Transactional
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            UserRepository userRepository,
            OrderRepository orderRepository // И это
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> getUserOrders(Long userId) {
        // репозиторий заказов для прямого запроса
        return orderRepository.findByUser(
                userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );
    }
}