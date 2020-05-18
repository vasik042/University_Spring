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

    @Query(value = "SELECT * FROM application  WHERE entrant_id = ?1 ORDER BY priority", nativeQuery = true)
    List<Application> findByEntrantId(int id);

    @Query(value = "SELECT * FROM application  WHERE faculty_id = ?1 ORDER BY GPA DESC", nativeQuery = true)
    List<Application> findByFacultyId(int id);

    @Query(value = "SELECT priority FROM application  WHERE application_id = ?1", nativeQuery = true)
    Integer findPriorityById(int id);

    @Transactional
    @Modifying
    void deleteByFacultyId(int id);

    @Transactional
    @Modifying
    void deleteByEntrantId(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE application SET priority = ?1 WHERE application_id = ?2", nativeQuery = true)
    void changePriority(int priority, int id);
}
