package com.example.demo.repository;

import com.example.demo.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport,Integer> {
    List<Sport> findByDate(String date);
}