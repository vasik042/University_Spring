package com.example.demo.controllers;

import com.example.demo.Dtos.CustomUserDetails;
import com.example.demo.Services.*;
import com.example.demo.Services.subjects.GradeService;
import com.example.demo.Services.userServices.AdminService;
import com.example.demo.Services.userServices.EntrantService;
import com.example.demo.Services.userServices.MailSenderService;
import com.example.demo.entities.Application;
import com.example.demo.entities.subjects.Grade;
import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private EntrantService entrantService;
    private GradeService gradeService;
    private ApplicationService applicationService;
    private FacultyService facultyService;
    private MailSenderService mailSenderService;
    private SuperAdminController superAdminController;
    private AdminService adminService;

    @Autowired
    public CabinetController(EntrantService entrantService,
                             GradeService gradeService,
                             ApplicationService applicationService,
                             FacultyService facultyService,
                             MailSenderService mailSenderService,
                             SuperAdminController superAdminController,
                             AdminService adminService) {
        this.entrantService = entrantService;
        this.gradeService = gradeService;
        this.applicationService = applicationService;
        this.facultyService = facultyService;
        this.mailSenderService = mailSenderService;
        this.superAdminController = superAdminController;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCabinet(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        if (request.getSession().getAttribute("role") == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();

            HttpSession session = request.getSession(true);

            Entrant entrant = entrantService.findByEmail(customUser.getUsername());

            if (entrant == null){
                Admin admin = adminService.findByEmail(customUser.getUsername());

                if (admin != null){
                    session.setAttribute("UserId", admin.getId());
                    session.setAttribute("role", admin.getRole());

                    return getCabinet(request);
                }else {
                    return "login";
                }
            }else {
                if(entrant.getRole().equals(Roles.NOT_VERIFIED_EMAIL_ENTRANT.name())){
                    return "login";
                }else {
                    session.setAttribute("UserId", entrant.getId());
                    session.setAttribute("role", entrant.getRole());
                    session.setAttribute("applicationsLeft", entrant.getApplicationsLeft());

                    return getCabinet(request);
                }
            }
        }

        String role = (String) request.getSession(true).getAttribute("role");

        if (role.equals(Roles.SUPER_ADMIN.name())){
            return "superAdminCabinet";
        }else if(role.equals(Roles.ENTRANT.name())
                || role.equals(Roles.PAST.name())
                || role.equals(Roles.NOT_VERIFIED_ENTRANT.name())
                || role.equals(Roles.NOT_PAST.name())){

            Integer id = (Integer) request.getSession().getAttribute("UserId");

            Entrant entrant = entrantService.findById(id);

            request.setAttribute("entrant", entrant);
            request.setAttribute("applications", applicationService.findByEntrantId(entrant.getId()));
            request.setAttribute("grades", gradeService.findByEntrantId(entrant.getId()));

        }else if (role.equals(Roles.ADMIN.name())){

            List<Entrant> entrants = entrantService.findByRole(Roles.NOT_VERIFIED_ENTRANT.name());
            List<Grade> grades = gradeService.findAll();

            request.setAttribute("entrants", entrants);
            request.setAttribute("grades", grades);
        }else {
            return "index";
        }

        return "cabinet";
    }

    @RequestMapping(value = "/admin/activateEntrant", method = RequestMethod.GET)
    public String activateEntrant(@RequestParam(name = "id") int id, HttpServletRequest request) {

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

    @RequestMapping(value = "/admin/deleteEntrant", method = RequestMethod.GET)
    public String deleteEntrant(@RequestParam(name = "id") int id, HttpServletRequest request) {

        String email = entrantService.findEmailById(id);

        gradeService.deleteByEntrantId(id);
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

    @RequestMapping(value = "/entrant/deleteApplication", method = RequestMethod.GET)
    public String deleteApplication(@RequestParam(name = "id") int id, HttpServletRequest request) {

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

    @RequestMapping(value = "/entrant/increasePriority", method = RequestMethod.GET)
    public String increasePriority(@RequestParam(name = "id") int id, HttpServletRequest request){

        changePriority(id, request, 1);

        return getCabinet(request);
    }

    @RequestMapping(value = "/entrant/reducePriority", method = RequestMethod.GET)
    public String reducePriority(@RequestParam(name = "id") int id, HttpServletRequest request){

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
