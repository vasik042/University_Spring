package com.example.demo.Services;

import com.example.demo.entities.EntrantSubject;
import com.example.demo.repositories.EntrantSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrantSubjectService {

    private EntrantSubjectRepository entrantSubjectRepo;

    @Autowired
    public EntrantSubjectService(EntrantSubjectRepository entrantSubjectRepo) {
        this.entrantSubjectRepo = entrantSubjectRepo;
    }

    public void save(EntrantSubject entrantSubject){
        entrantSubjectRepo.save(entrantSubject);
    }

    public List<EntrantSubject> findByEntrantId(int id){
        return entrantSubjectRepo.findByEntrantId(id);
    }

    public List<EntrantSubject> findAll() {
        return entrantSubjectRepo.findAll();
    }

    public void deleteByEntrantId(int id) {
        entrantSubjectRepo.deleteByEntrantId(id);
    }
}
