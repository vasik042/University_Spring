package com.example.demo.Services;

import com.example.demo.entities.FacultySubject;
import com.example.demo.repositories.FacultySubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultySubjectService {

    private FacultySubjectRepository facultySubjectRepo;

    @Autowired
    public FacultySubjectService(FacultySubjectRepository facultySubjectRepo) {
        this.facultySubjectRepo = facultySubjectRepo;
    }

    public List<FacultySubject> findAll(){
        return facultySubjectRepo.findAll();
    }

    public List<FacultySubject> getFacultySubjects(int id){
        return facultySubjectRepo.findByFacultyId(id);
    }
}
