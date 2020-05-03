package com.example.demo.repositories;

import com.example.demo.entities.EntrantSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrantSubjectRepository  extends JpaRepository<EntrantSubject, Integer> {

    public List<EntrantSubject> findByEntrantId(int id);
}
