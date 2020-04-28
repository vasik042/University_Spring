package com.example.demo.repositories;

import com.example.demo.entities.FacultySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Integer> {

    @Query("select s from faculty_subject s where s.faculty_id = ?1")
    List<FacultySubject> findByFacultyId(int id);
}
