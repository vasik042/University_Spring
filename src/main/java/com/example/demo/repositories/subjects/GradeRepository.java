package com.example.demo.repositories.subjects;

import com.example.demo.entities.subjects.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query(value = "SELECT * FROM entrant_grade ORDER BY subject_id", nativeQuery = true)
    List<Grade> findAllOrderBySubjectId();

    List<Grade> findByEntrantId(int id);

    @Transactional
    @Modifying
    void deleteByEntrantId(int id);
}
