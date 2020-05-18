package com.example.demo.Services.subjects;

import com.example.demo.entities.subjects.Grade;
import com.example.demo.repositories.subjects.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private GradeRepository gradeRepo;

    @Autowired
    public GradeService(GradeRepository gradeRepo) {
        this.gradeRepo = gradeRepo;
    }

    public List<Grade> findByEntrantId(int id){
        return gradeRepo.findByEntrantId(id);
    }

    public List<Grade> findAll() {
        return gradeRepo.findAllOrderBySubjectId();
    }

    public void deleteByEntrantId(int id) {
        gradeRepo.deleteByEntrantId(id);
    }

    public void saveAll(List<Grade> grades) {
        gradeRepo.saveAll(grades);
    }
}
