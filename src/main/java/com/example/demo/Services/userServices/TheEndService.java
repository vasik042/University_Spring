package com.example.demo.Services.userServices;

import com.example.demo.Services.ApplicationService;
import com.example.demo.Services.FacultyService;
import com.example.demo.entities.Application;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TheEndService {

    private FacultyService facultyService;
    private ApplicationService applicationService;
    private MailSenderService mailSender;
    private EntrantService entrantService;

    @Autowired
    public TheEndService(FacultyService facultyService, ApplicationService applicationService,
                         MailSenderService mailSender, EntrantService entrantService) {
        this.facultyService = facultyService;
        this.applicationService = applicationService;
        this.mailSender = mailSender;
        this.entrantService = entrantService;
    }

    public void theEnd() {

        List<Application> passedApplications = new ArrayList<>();

        forEachPriority(passedApplications, 3);

        mailSender.sendPassMassage(passedApplications);

        applicationService.deleteAll(applicationService.findAll());
        applicationService.addAll(passedApplications);
    }

    private void forEachPriority(List<Application> passedApplications, int maxPriority) {
        List<Faculty> faculties = facultyService.findAll();

        while (true){
            int beforeChecking = passedApplications.size();

            entrantService.setRoles(Roles.ENTRANT.name());
            for (int i = 1; i <= maxPriority; i++) {
                forEachFaculty(faculties, passedApplications, i);
            }

            int afterChecking = passedApplications.size();

            if(beforeChecking == afterChecking){
                break;
            }
        }
    }

    private void forEachFaculty(List<Faculty> faculties, List<Application> passedApplications, int priority) {
        boolean noMoreApplications = false;

        while (!noMoreApplications){
            noMoreApplications = false;
            for (Faculty faculty: faculties) {
                if(forEachApplication(faculty, passedApplications, priority)){
                    noMoreApplications = true;
                }
            }
        }
    }

    private boolean forEachApplication(Faculty faculty, List<Application> passedApplications, int priority) {
        //Sorted by GPA
        List<Application> facultyApplications = applicationService.findByFacultyId(faculty.getId());
        boolean noMoreApplications = true;

        int placesLeft = faculty.getPlaces();
        for (Application application: passedApplications) {
            if(application.getFaculty().equals(faculty)){
                placesLeft--;
            }
        }

        boolean haveChance = false;
        for (Application application: facultyApplications) {
            if(placesLeft == 0){
                break;
            }

            //to prevent situations when entrant can pass to first priority faculty,
            //but there is one application with higher priority that is not deleted yet
            if((application.getPriority() > priority && !passedApplications.contains(application))){
                haveChance = true;
            }

            if(application.getPriority() == priority && !passedApplications.contains(application) && !application.getEntrant().getRole().equals("HAS_CHANCE")){

                passedApplications.add(application);
                applicationService.deleteByEntrantId(application.getEntrant().getId());

                placesLeft--;
                noMoreApplications = false;
            }else if(application.getPriority() == priority && !passedApplications.contains(application) && haveChance){
                application.getEntrant().setRole("HAS_CHANCE");
            }
        }

        return noMoreApplications;
    }
}
