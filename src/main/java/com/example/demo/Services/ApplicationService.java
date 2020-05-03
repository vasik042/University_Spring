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

        for (FacultySubject fs: faculty.getFacultySubjects()) {
            for (EntrantSubject es: entrant.getSubjects()) {
                if(es.getSubjectName().equals(fs.getSubjectName())){
                    gpa += es.getGrade()*fs.getCoefficient();
                }
            }
        }

        gpa += (entrant.getSchoolGPA()/12 + 1) * 10;

        Application application = new Application(gpa, entrant, faculty);
        applicationRepo.save(application);
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
}
