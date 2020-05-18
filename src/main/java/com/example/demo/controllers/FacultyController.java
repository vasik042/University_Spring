package com.example.demo.controllers;

import com.example.demo.Services.*;
import com.example.demo.Services.subjects.CoefficientService;
import com.example.demo.Services.subjects.GradeService;
import com.example.demo.Services.userServices.EntrantService;
import com.example.demo.entities.Application;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.subjects.Coefficient;
import com.example.demo.entities.subjects.Grade;
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
    private GradeService gradeService;
    private ApplicationService applicationService;
    private FacultyService facultyService;
    private CoefficientService coefficientService;

    @Autowired
    public FacultyController(EntrantService entrantService,
                             GradeService gradeService,
                             ApplicationService applicationService,
                             FacultyService facultyService,
                             CoefficientService coefficientService) {
        this.entrantService = entrantService;
        this.gradeService = gradeService;
        this.applicationService = applicationService;
        this.facultyService = facultyService;
        this.coefficientService = coefficientService;
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String getFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("applications", applicationService.findByFacultyId(id));
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("faculty", facultyService.findById(id));

        List<Coefficient> facultyCoefficients = coefficientService.getCoefficients(id);

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
                    List<Grade> grades = gradeService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));

                    for (Coefficient c : facultyCoefficients) {
                        for (Grade g : grades) {
                            if (c.getSubject().equals(g.getSubject())) {
                                i++;
                            }
                        }
                    }
                    if (i == facultyCoefficients.size()) {
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

        request.setAttribute("coefs", facultyCoefficients);

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
