package com.example.demo.repositories;

import com.example.demo.entities.EntrantSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrantSubjectRepository  extends JpaRepository<EntrantSubject, Integer> {

    @Query("select s from entrant_subject s where s.entrant_id = ?1")
    List<EntrantSubject> findByEntrantId(int id);
}
