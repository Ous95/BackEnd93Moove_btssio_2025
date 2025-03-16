package com.example.demo.repository;

import com.example.demo.model.Tatoueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatoueurRepository extends JpaRepository<Tatoueur,Integer> {
    List<Tatoueur> findByStyle(String style);
}