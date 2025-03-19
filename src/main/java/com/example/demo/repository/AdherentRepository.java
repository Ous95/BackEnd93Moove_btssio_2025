package com.example.demo.repository;

import com.example.demo.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent,Integer> {
    List<Adherent> findByStyle(String style);
}