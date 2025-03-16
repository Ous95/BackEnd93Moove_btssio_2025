package com.example.demo.repository;

import com.example.demo.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser,Integer> {
    CustomUser findByUsername(String username);
}
