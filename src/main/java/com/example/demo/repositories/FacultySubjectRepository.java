package com.example.demo.repositories;

import com.example.demo.entities.FacultySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Integer> {

    List<FacultySubject> findByFacultyId(int id);

    @Transactional
    @Modifying
    void deleteByFacultyId(int id);
}
