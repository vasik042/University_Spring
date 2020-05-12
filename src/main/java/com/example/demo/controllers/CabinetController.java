package com.example.demo.controllers;

import com.example.demo.Services.*;
import com.example.demo.Subjects;
import com.example.demo.entities.Application;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.FacultySubject;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class CabinetController {

    private EntrantService entrantService;
    private EntrantSubjectService entrantSubjectService;
    private ApplicationService applicationService;
    private FacultyService facultyService;
    private MailSenderService mailSenderService;
    private SuperAdminController superAdminController;

    @Autowired
    public CabinetController(EntrantService entrantService,
                             EntrantSubjectService entrantSubjectService,
                             ApplicationService applicationService,
                             FacultyService facultyService,
                             MailSenderService mailSenderService,
                             SuperAdminController superAdminController) {
        this.entrantService = entrantService;
        this.entrantSubjectService = entrantSubjectService;
        this.applicationService = applicationService;
        this.facultyService = facultyService;
        this.mailSenderService = mailSenderService;
        this.superAdminController = superAdminController;
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String getCabinet(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        String role = (String) request.getSession().getAttribute("role");

        if (role.equals(Roles.SUPER_ADMIN.name())){
            return "superAdminCabinet";
        }else if(role.equals(Roles.ENTRANT.name())){

            Entrant entrant = entrantService.findById((Integer) request.getSession().getAttribute("UserId"));

            request.setAttribute("entrant", entrant);
            request.setAttribute("applications", applicationService.findByEntrantId(entrant.getId()));

            List<EntrantSubject> subjects = entrantSubjectService.findByEntrantId(entrant.getId());
            for (EntrantSubject es: subjects) {
                for (Subjects subject: Subjects.values()) {
                    if(es.getSubjectName().equals(subject.name())){
                        es.setSubjectName(subject.getUkrName());
                    }
                }
            }

            request.setAttribute("subjects", subjects);

        }else if (role.equals(Roles.ADMIN.name())){

            List<Entrant> entrants = entrantService.findByRole(Roles.NOT_VERIFIED_ENTRANT.name());
            List<EntrantSubject> subjects = new ArrayList<>();

            request.setAttribute("entrants", entrants);

            for (Entrant e:entrants) {
                subjects.addAll(entrantSubjectService.findByEntrantId(e.getId()));
            }

            request.setAttribute("subjects", subjects);
        }else {
            return "index";
        }

        return "cabinet";
    }

    @RequestMapping(value = "/activateEntrant", method = RequestMethod.GET)
    public String activateEntrant(@RequestParam(name = "id") int id, HttpServletRequest request) {

        if(!request.getSession().getAttribute("role").equals(Roles.SUPER_ADMIN.name()) || !request.getSession().getAttribute("role").equals(Roles.ADMIN.name())){
            return "index";
        }

        entrantService.changeRole(id, Roles.ENTRANT.name());

        String email = entrantService.findEmailById(id);

        String subject = "Вашу анкету підтверджено";
        String text = "Тепер ви можете подавати заявки на вступ до факультетів нашого навчального закладу.";

        mailSenderService.message(email, subject, text);

        if (request.getSession().getAttribute("role").equals(Roles.SUPER_ADMIN.name())){
            return superAdminController.superAdminCabinetEntrants(request);
        }else {
            return getCabinet(request);
        }
    }

    @RequestMapping(value = "/deleteEntrant", method = RequestMethod.GET)
    public String deleteEntrant(@RequestParam(name = "id") int id, HttpServletRequest request) {

        if(!request.getSession().getAttribute("role").equals(Roles.SUPER_ADMIN.name()) || !request.getAttribute("role").equals(Roles.ADMIN.name())){
            return "index";
        }

        String email = entrantService.findEmailById(id);

        entrantSubjectService.deleteByEntrantId(id);
        applicationService.deleteByEntrantId(id);
        entrantService.deleteById(id);

        String subject = "Вашу анкету відхилено";
        String text = "Ваша анкета була неправильно заповнена або містила неправдиві данні.";

        mailSenderService.message(email, subject, text);

        if (request.getSession().getAttribute("role").equals(Roles.SUPER_ADMIN.name())){
            return superAdminController.superAdminCabinetEntrants(request);
        }else {
            return getCabinet(request);
        }
    }

    @RequestMapping(value = "/deleteApplication", method = RequestMethod.GET)
    public String deleteApplication(@RequestParam(name = "id") int id, HttpServletRequest request) {

        if(!request.getSession().getAttribute("role").equals(Roles.ENTRANT.name())){
            return "index";
        }

        Integer priority = applicationService.findPriorityById(id);
        applicationService.deleteById(id);

        Entrant entrant = entrantService.findById((Integer) request.getSession().getAttribute("UserId"));

        for (Application a: entrant.getApplications()) {
            if (a.getPriority() > priority){
                applicationService.changePriority(a.getId(), a.getPriority()-1);
                a.setPriority(a.getPriority() - 1);
            }
        }

        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("applications", applicationService.findByEntrantId((Integer)request.getSession().getAttribute("UserId")));

        entrantService.changeApplicationsLeft(entrant.getId(), entrant.getApplicationsLeft()+1);
        request.getSession().setAttribute("applicationsLeft", entrant.getApplicationsLeft()+1);

        return getCabinet(request);
    }

    @RequestMapping(value = "/increasePriority", method = RequestMethod.GET)
    public String increasePriority(@RequestParam(name = "id") int id, HttpServletRequest request){

        if(!request.getSession().getAttribute("role").equals(Roles.ENTRANT.name())){
            return "index";
        }

        changePriority(id, request, 1);

        return getCabinet(request);
    }

    @RequestMapping(value = "/reducePriority", method = RequestMethod.GET)
    public String reducePriority(@RequestParam(name = "id") int id, HttpServletRequest request){

        if(!request.getSession().getAttribute("role").equals(Roles.ENTRANT.name())){
            return "index";
        }

        changePriority(id, request, -1);

        return getCabinet(request);
    }

    private void changePriority(int id, HttpServletRequest request, int i){
        Integer priority = applicationService.findPriorityById(id);
        List<Application> applications = applicationService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));

        for (Application a: applications) {
            if (a.getPriority() == priority - i){
                applicationService.changePriority(a.getId(), priority);
                a.setPriority(priority);
            }
            if (a.getId() == id){
                a.setPriority(priority - i);
            }
        }
        applicationService.changePriority(id, priority - i);

        applications.sort(Comparator.comparing(Application::getPriority));

        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("applications", applications);
    }
}
