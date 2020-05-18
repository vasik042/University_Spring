package com.example.demo.repositories.subjects;

import com.example.demo.entities.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findByEnglishName(String engName);
}
