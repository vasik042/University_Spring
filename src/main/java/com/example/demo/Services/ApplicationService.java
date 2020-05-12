package com.example.demo.Services;

import com.example.demo.entities.*;
import com.example.demo.entities.userEntities.Entrant;
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

    public void save(Entrant entrant, Faculty faculty){
        float gpa = 0;
        int count = faculty.getFacultySubjects().size();

        for (FacultySubject fs: faculty.getFacultySubjects()) {
            for (EntrantSubject es: entrant.getSubjects()) {
                if(es.getSubjectName().equals(fs.getSubjectName())){
                    gpa += es.getGrade()*fs.getCoefficient();
                    count--;
                }
            }
        }

        gpa += (entrant.getSchoolGPA()/12 + 1) * 10;

        if(count == 0){
            Application application = new Application(gpa, entrant, faculty);
            applicationRepo.save(application);
        }else {
            //TODO mail send
        }
    }

    public void deleteById(int id){
        applicationRepo.deleteById(id);
    }

    public List<Application> findByEntrantId(int id){
        return applicationRepo.findByEntrantId(id);
    }

    public List<Application> findByFacultyId(int id){
        return applicationRepo.findByFacultyId(id);
    }

    public void deleteAll(List<Application> applications) {
        applicationRepo.deleteAll(applications);
    }

    public void deleteByFacultyId(int id) {
        applicationRepo.deleteByFacultyId(id);
    }

    public void deleteByEntrantId(int id) {
        applicationRepo.deleteByEntrantId(id);
    }

    public List<Application> findAll() {
        return applicationRepo.findAll();
    }

    public Integer findPriorityById(int id){
        return applicationRepo.findPriorityById(id);
    }

    public void changePriority(int id, int priority) {
        applicationRepo.changePriority(priority, id);
    }
}
