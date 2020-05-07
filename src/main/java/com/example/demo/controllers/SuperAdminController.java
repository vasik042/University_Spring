package com.example.demo.controllers;

import com.example.demo.Services.*;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SuperAdminController {

    private EntrantService entrantService;
    private EntrantSubjectService entrantSubjectService;
    private FacultyService facultyService;
    private FacultySubjectService facultySubjectService;
    private AdminService adminService;

    @Autowired
    public SuperAdminController(EntrantService entrantService, EntrantSubjectService entrantSubjectService,
                                FacultyService facultyService, FacultySubjectService facultySubjectService,
                                AdminService adminService) {
        this.entrantService = entrantService;
        this.entrantSubjectService = entrantSubjectService;
        this.facultyService = facultyService;
        this.facultySubjectService = facultySubjectService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/superAdminCabinetFaculties", method = RequestMethod.GET)
    public String superAdminCabinetFaculties(HttpServletRequest request) {

        request.setAttribute("choose", "faculties");
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("subjects", facultySubjectService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/superAdminCabinetAdmins", method = RequestMethod.GET)
    public String superAdminCabinetAdmins(HttpServletRequest request) {

        request.setAttribute("choose", "admins");
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("admins", adminService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/superAdminCabinetEntrants", method = RequestMethod.GET)
    public String superAdminCabinetEntrants(HttpServletRequest request) {

        request.setAttribute("choose", "entrants");
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("subjects", entrantSubjectService.findAll());
        request.setAttribute("entrants", entrantService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public String addFaculty(HttpServletRequest request) {
        return "addFaculty";
    }

    @RequestMapping(value = "/editFaculty", method = RequestMethod.GET)
    public String editFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("faculty", facultyService.findById(id));

        return "addFaculty";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public String addAdmin(HttpServletRequest request) {
        return "addAdmin";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.GET)
    public String editAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("admin", adminService.findById(id));

        return "addAdmin";
    }

    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.GET)
    public String deleteAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        adminService.deleteById(id);

        return superAdminCabinetAdmins(request);
    }
}
