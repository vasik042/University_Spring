package com.example.demo.repositories;

import com.example.demo.entities.FacultySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Integer> {

    @Query(value = "SELECT * FROM faculty_subject  WHERE faculty_id = ?1", nativeQuery = true)
    List<FacultySubject> findByFacultyId(int id);
}
