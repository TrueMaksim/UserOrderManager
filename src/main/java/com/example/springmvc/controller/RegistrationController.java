package com.example.springmvc.controller;

import com.example.springmvc.model.Role;
import com.example.springmvc.model.UserAuth;
import com.example.springmvc.repository.UserAuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Set;

@Controller
public class RegistrationController {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserAuthRepository userAuthRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {  // проверка существования пользователя
        if (userAuthRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Имя пользователя уже существует");
            return "registration";
        }
// создание нового пользователя
        UserAuth user = new UserAuth();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(Set.of(Role.USER));

        userAuthRepository.save(user);
        return "redirect:/login";
    }
}