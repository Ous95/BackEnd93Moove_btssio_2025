package com.example.demo.repository;

import com.example.demo.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    List<Projet> findByClientId(int clientId);
}
