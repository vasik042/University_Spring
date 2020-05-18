package com.example.demo.Services.subjects;

import com.example.demo.entities.subjects.Subject;
import com.example.demo.repositories.subjects.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepo;

    @Autowired
    public SubjectService(SubjectRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public Subject findByEnglishName(String engName) {
        return subjectRepo.findByEnglishName(engName);
    }

    public List<Subject> findAll(){
        return subjectRepo.findAll();
    }
}
