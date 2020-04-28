package com.example.demo.Services;

import com.example.demo.entities.Entrant;
import com.example.demo.repositories.EntrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrantService {

    private EntrantRepository entrantRepo;

    @Autowired
    public EntrantService(EntrantRepository entrantRepo) {
        this.entrantRepo = entrantRepo;
    }

    public void save(Entrant entrant){
        entrantRepo.save(entrant);
    }

    public void deleteById(int id){
        entrantRepo.deleteById(id);
    }

    public Entrant findById(int id){
        return entrantRepo.findById(id).get();
    }

    public List<Entrant> getAll(){
        return entrantRepo.findAll();
    }
}
