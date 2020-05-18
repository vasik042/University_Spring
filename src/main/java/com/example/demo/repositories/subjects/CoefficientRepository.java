package com.example.demo.repositories.subjects;

import com.example.demo.entities.subjects.Coefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CoefficientRepository extends JpaRepository<Coefficient, Integer> {

    List<Coefficient> findByFacultyId(int id);

    @Transactional
    @Modifying
    void deleteByFacultyId(int id);
}
