package com.example.demo.repository;

import com.example.demo.model.Tatoueura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatoueuraRepository extends JpaRepository<Tatoueura,Integer> {
    List<Tatoueura> findByStyle(String style);
}