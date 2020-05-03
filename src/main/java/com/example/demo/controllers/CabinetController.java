package com.example.demo.controllers;

import com.example.demo.Services.ApplicationService;
import com.example.demo.Services.EntrantService;
import com.example.demo.Services.EntrantSubjectService;
import com.example.demo.Services.FacultyService;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CabinetController {

    private EntrantService entrantService;
    private EntrantSubjectService entrantSubjectService;
    private ApplicationService applicationService;
    private FacultyService facultyService;

    @Autowired
    public CabinetController(EntrantService entrantService,
                             EntrantSubjectService entrantSubjectService,
                             ApplicationService applicationService,
                             FacultyService facultyService) {
        this.entrantService = entrantService;
        this.entrantSubjectService = entrantSubjectService;
        this.applicationService = applicationService;
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String getCabinet(HttpServletRequest request) {

        if(((String) request.getSession().getAttribute("role")).equals(Roles.ENTRANT.name())){
            request.setAttribute("applications", applicationService.findByEntrantId((Integer)request.getSession().getAttribute("UserId")));
        }else if (((String) request.getSession().getAttribute("role")).equals(Roles.ADMIN.name())){

            List<Entrant> entrants = entrantService.findByRole(Roles.NOT_VERIFIER_ENTRANT.name());
            List<EntrantSubject> subjects = new ArrayList<>();

            request.setAttribute("entrants", entrants);

            for (Entrant e:entrants) {
                subjects.addAll(entrantSubjectService.findByEntrantId(e.getId()));
            }

            request.setAttribute("subjects", subjects);
        }
        request.setAttribute("faculties", facultyService.findAll());

        return "cabinet";
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activate(@RequestParam(name = "id") int id, HttpServletRequest request) {
        entrantService.changeRole(id, Roles.ENTRANT.name());
        return getCabinet(request);
    }
}
