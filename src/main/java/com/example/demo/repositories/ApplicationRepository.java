package com.example.demo.repositories;

import com.example.demo.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application, Integer> {

    @Query("select a from application a where a.entrant_id = ?1")
    List<Application> findByEntrantId(int id);

    @Query("select a from application a where a.faculty_id = ?1 ordered by a.GPA ASC")
    List<Application> findByFacultyId(int id);
}
