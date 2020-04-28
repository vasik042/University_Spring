package com.example.demo.Services;

import com.example.demo.entities.Application;
import com.example.demo.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepo;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    public void save(Application application){
        applicationRepo.save(application);
    }

    public void deleteById(int id){
        applicationRepo.deleteById(id);
    }

    public List<Application> getAll(){
        return applicationRepo.findAll();
    }

    List<Application> findByEntrantId(int id){
        return applicationRepo.findByEntrantId(id);
    }

    List<Application> findByFacultyId(int id){
        return applicationRepo.findByFacultyId(id);
    }
}
