package com.example.demo.repositories;

import com.example.demo.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository  extends  JpaRepository<Application, Integer>{

//    @Query(value = "SELECT * FROM application  WHERE entrant_id = ?1", nativeQuery = true)
    List<Application> findByEntrantId(int id);

    @Query(value = "SELECT * FROM application  WHERE faculty_id = ?1 ORDER BY GPA DESC", nativeQuery = true)
    List<Application> findByFacultyId(int id);

}
