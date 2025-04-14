package com.example.springmvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birth;

    @Min(value = 1, message = "Возраст должен быть не меньше 1")
    private Integer age;

    @Email(message = "Некорректный формат email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Имя не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Имя не может быть пустым") String name) {
        this.name = name;
    }

    public @Past(message = "Дата рождения должна быть в прошлом") LocalDate getBirth() {
        return birth;
    }

    public void setBirth(@Past(message = "Дата рождения должна быть в прошлом") LocalDate birth) {
        this.birth = birth;
    }

    public @Min(value = 1, message = "Возраст должен быть не меньше 1") Integer getAge() {
        return age;
    }

    public void setAge(@Min(value = 1, message = "Возраст должен быть не меньше 1") Integer age) {
        this.age = age;
    }

    public @Email(message = "Некорректный формат email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Некорректный формат email") String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}