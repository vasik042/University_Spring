package com.example.demo.repositories;

import com.example.demo.entities.Entrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrantRepository  extends JpaRepository<Entrant, Integer> {
}
