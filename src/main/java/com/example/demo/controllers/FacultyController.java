package com.example.demo.controllers;

import com.example.demo.Services.*;
import com.example.demo.Subjects;
import com.example.demo.entities.Application;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.FacultySubject;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FacultyController {

    private EntrantService entrantService;
    private EntrantSubjectService entrantSubjectService;
    private ApplicationService applicationService;
    private FacultyService facultyService;
    private FacultySubjectService facultySubjectService;

    @Autowired
    public FacultyController(EntrantService entrantService,
                             EntrantSubjectService entrantSubjectService,
                             ApplicationService applicationService,
                             FacultyService facultyService,
                             FacultySubjectService facultySubjectService) {
        this.entrantService = entrantService;
        this.entrantSubjectService = entrantSubjectService;
        this.applicationService = applicationService;
        this.facultyService = facultyService;
        this.facultySubjectService = facultySubjectService;
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String getFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("entrants", applicationService.findByFacultyId(id));
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("faculty", facultyService.findById(id));

        List<FacultySubject> facultySubjects = facultySubjectService.getFacultySubjects(id);

        String role = (String) request.getSession().getAttribute("role");

        //check if logged
        if (role != null) {
            //check if entrant is verified
            if (role.equals(Roles.NOT_VERIFIED_ENTRANT.name())) {
                request.setAttribute("canReg", 1);
            } else if (role.equals(Roles.ENTRANT.name())) {
                //check if entrant able to make application
                Integer applicationsLeft = (Integer) request.getSession().getAttribute("applicationsLeft");
                if (applicationsLeft > 0) {
                    //check if entrant have necessary subjects
                    int i = 0;
                    List<EntrantSubject> entrantSubjects = entrantSubjectService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));

                    for (FacultySubject fs : facultySubjects) {
                        for (EntrantSubject es : entrantSubjects) {
                            if (fs.getSubjectName().equals(es.getSubjectName())) {
                                i++;
                            }
                        }
                    }
                    if (i == facultySubjects.size()) {
                        request.setAttribute("canReg", 0);
                    } else {
                        request.setAttribute("canReg", 4);
                    }

                    //check if entrant already have application
                    List<Application> applications = applicationService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));
                    for (Application a : applications) {
                        if (a.getFaculty().getId() == id) {
                            request.setAttribute("canReg", 3);
                        }
                    }
                } else {
                    request.setAttribute("canReg", 2);
                }
            }
        }else {
            request.setAttribute("canReg", 5);
        }

        //translate subject names
        for (FacultySubject fs: facultySubjects) {
            for (Subjects subject: Subjects.values()) {
                if(fs.getSubjectName().equals(subject.name())){
                    fs.setSubjectName(subject.getUkrName());
                }
            }
        }
        request.setAttribute("subjects", facultySubjects);

        return "faculty";
    }

    @RequestMapping(value = "/makeApplication", method = RequestMethod.GET)
    public String makeApplication(@RequestParam(name = "id") int id, HttpServletRequest request){

        Entrant entrant = entrantService.findById((Integer) request.getSession().getAttribute("UserId"));
        List<Application> applications = applicationService.findByEntrantId(entrant.getId());

        for (Application a: applications) {
            if(a.getFaculty().getId() == id){
                return getFaculty(id, request);
            }
        }

        Faculty faculty = facultyService.findById(id);

        applicationService.save(entrant, faculty);

        entrantService.changeApplicationsLeft(entrant.getId(), entrant.getApplicationsLeft()-1);
        request.getSession(true).setAttribute("applicationsLeft", entrant.getApplicationsLeft()-1);


        return getFaculty(id, request);
    }
}
