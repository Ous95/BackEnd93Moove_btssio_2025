package com.example.demo.repository;

import com.example.demo.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultureRepository extends JpaRepository<Culture,Integer> {
    List<Culture> findByHoraire(String horaire);
}