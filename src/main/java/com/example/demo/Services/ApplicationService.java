package com.example.demo.Services;

import com.example.demo.Services.userServices.MailSenderService;
import com.example.demo.entities.*;
import com.example.demo.entities.subjects.Coefficient;
import com.example.demo.entities.subjects.Grade;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepo;
    private MailSenderService mailSender;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepo, MailSenderService mailSender) {
        this.applicationRepo = applicationRepo;
        this.mailSender = mailSender;
    }

    public void save(Entrant entrant, Faculty faculty){
        float gpa = 0;
        int count = faculty.getCoefficients().size();

        for (Coefficient c: faculty.getCoefficients()) {
            for (Grade g: entrant.getGrades()) {
                if(g.getSubject().equals(c.getSubject())){
                    gpa += g.getGrade()*c.getCoefficient();
                    count--;
                }
            }
        }

        gpa += (entrant.getSchoolGPA()/12 + 1) * 10;

        if(count == 0){
            Application application = new Application(gpa, entrant, faculty);
            applicationRepo.save(application);
        }else {
            mailSender.message(entrant.getEmail(), "Вашу заяву було видаленно",
                    "В звязку зі зміною предметів, необхідних для вступу на факультет, вашу заяву було видаленно.");
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

    public void addAll(List<Application> applications) {
        applicationRepo.saveAll(applications);
    }
}
