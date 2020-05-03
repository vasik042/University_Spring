package com.example.demo.repositories;

import com.example.demo.entities.EntrantSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrantSubjectRepository  extends JpaRepository<EntrantSubject, Integer> {

//    @Query(value = "SELECT * FROM entrant_subject  WHERE entrant_id = ?1", nativeQuery = true)
    public List<EntrantSubject> findByEntrantId(int id);
}
