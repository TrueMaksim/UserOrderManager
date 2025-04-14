package com.example.springmvc.controller;

import com.example.springmvc.model.Order;
import com.example.springmvc.model.OrderService;
import com.example.springmvc.model.User;
import com.example.springmvc.repository.OrderRepository;
import com.example.springmvc.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller // Аннотация Spring MVC контроллера
@RequestMapping("/web") // Базовый URL для всех методов контроллера
@Validated // Включение валидации для всего контроллера
public class GreetingController {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    @Getter
    private final OrderService orderService;
    @Autowired // Внедрение зависимостей через конструктор
    public GreetingController(UserRepository userRepository, OrderRepository orderRepository, OrderService orderService) {
     this.userRepository = userRepository;
     this.orderRepository = orderRepository;
     this.orderService = orderService;
    }

    // Добавление пустого пользователя в модель
    @ModelAttribute("user")
    public User getEmptyUser() {
        return new User();
    }
// Добавления заказа к пользователю.
@PostMapping("/users/{userId}/orders")
public String addOrderToUser(
        @PathVariable Long userId,
        @RequestParam String productName,
        @RequestParam Double price,
        RedirectAttributes redirectAttributes
) {
    try {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Order order = new Order();
        order.setProductName(productName);
        order.setPrice(price);
        order.setUser(user);
        user.getOrders().add(order); // Добавляем заказ в список пользователя
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Заказ добавлен!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Ошибка: " + e.getMessage());
    }
    return "redirect:/web/users";
}
    // Просмотр заказов пользователя
    @GetMapping("/users/{userId}/orders")
    public String getUserOrders(@PathVariable Long userId, Model model) {
        List<Order> orders = orderService.getUserOrders(userId); // Используйте сервис
        model.addAttribute("orders", orders);
        return "orders";
    }

    // Отображение формы добавления
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        return "main";
    }
// Обработка добавления пользователя
@PostMapping("/add")
public String addUser(
        @Valid @ModelAttribute("user") User user,
        BindingResult result,
        RedirectAttributes redirectAttributes,
        Model model
) {
    if (result.hasErrors()) {
        // Возвращаемся на страницу с формой без редиректа
        model.addAttribute("users", userRepository.findAll());
        return "main";
    }
    try {
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь добавлен!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Ошибка: " + e.getMessage());
    }
    return "redirect:/web/users";
}

//Главная страница со списком пользователей
@GetMapping("/main")
public String mainPage(Model model) {
    model.addAttribute("users", userRepository.findAll());

    model.addAttribute("user", new User());
    return "main";

}
    @GetMapping("/filter")
    public String filter(
            @RequestParam(required = false) String name,
            Model model
    ) {
        List<User> users = (name != null && !name.trim().isEmpty())
                ? userRepository.findByNameContainingIgnoreCase(name.trim()) // Используем правильный метод
                : userRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("user", new User()); // Для формы добавления
        return "main";
    }
    @PostMapping("/submit")
    public String handleForm(@ModelAttribute("user") User user) {
        // Сохранение пользователя
        return "redirect:/web/main";
    }
    @GetMapping("/somePage")
    public String showPage(Model model) {
        User user = new User();
        user.setName("");
        model.addAttribute("user", new User());
        return "main"; // Имя шаблона
    }


    // Список пользователей
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        return "main";
    }

}