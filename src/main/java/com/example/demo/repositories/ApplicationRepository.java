package com.example.demo.repositories;

import com.example.demo.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplicationRepository  extends  JpaRepository<Application, Integer>{

    List<Application> findByEntrantId(int id);

    @Query(value = "SELECT * FROM application  WHERE faculty_id = ?1 ORDER BY GPA DESC", nativeQuery = true)
    List<Application> findByFacultyId(int id);

    @Transactional
    @Modifying
    void deleteByFacultyId(int id);

    @Transactional
    @Modifying
    void deleteByEntrantId(int id);
}
