package com.example.demo.Services;

import com.example.demo.entities.Faculty;
import com.example.demo.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepo;

    @Autowired
    public FacultyService(FacultyRepository facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public List<Faculty> findAll(){
        return facultyRepo.findAll();
    }

    public Faculty findById(int id){
        return facultyRepo.findById(id).get();
    }
}
