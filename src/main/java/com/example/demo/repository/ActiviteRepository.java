package com.example.demo.repository;

import com.example.demo.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite,Integer> {
    List<Activite> findByDate(String date);
}