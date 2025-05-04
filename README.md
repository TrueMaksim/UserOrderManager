Веб-приложение для управления пользователями и заказами с ролевой моделью (USER/ADMIN).  
Реализовано на **Spring Boot** с использованием **Spring Security**, **Hibernate/JPA**, **MySQL** и **Thymeleaf**.

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)

## 📋 Основные функции
- **Аутентификация и авторизация** через Spring Security.
- **Ролевая модель**: 
  - Администратор: управление пользователями и правами.
  - Пользователь: просмотр и редактирование своих данных.
- **CRUD-операции** для:
  - Пользователей (имя, email, возраст, дата рождения).
  - Заказов (название товара, цена).
- Фильтрация пользователей по имени и ролям.
- Валидация данных (email, возраст, дата).

## 🛠 Технологии

  - Java 17, Spring Boot 3.1.5
  - Spring Security, JPA/Hibernate
  - Lombok, Validation API

  - Thymeleaf, HTML/CSS
- **База данных**: MySQL 8.0
- **Сборка**: Maven

## 🚀 Запуск проекта
1. **Клонируйте репозиторий**:
   ```bash
   git clone https://github.com/TrueMaksim/SpringMVCApp.git
