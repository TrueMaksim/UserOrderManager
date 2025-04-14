package com.example.springmvc.repository;

import com.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


   @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(concat('%', :name, '%'))")
   List<User> findByNameContainingIgnoreCase(@Param("name") String name);
}

