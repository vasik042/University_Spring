package com.example.demo.repositories;

import com.example.demo.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, Integer>{
}
