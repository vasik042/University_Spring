package com.example.demo.controllers;

import com.example.demo.Dtos.EmailAndPasswordDto;
import com.example.demo.Dtos.FacultyDto;
import com.example.demo.Services.*;
import com.example.demo.entities.Application;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.userEntities.Entrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SuperAdminController {

    private EntrantService entrantService;
    private EntrantSubjectService entrantSubjectService;
    private FacultyService facultyService;
    private FacultySubjectService facultySubjectService;
    private AdminService adminService;
    private ApplicationService applicationService;

    @Autowired
    public SuperAdminController(EntrantService entrantService, EntrantSubjectService entrantSubjectService,
                                FacultyService facultyService, FacultySubjectService facultySubjectService,
                                AdminService adminService, ApplicationService applicationService) {
        this.entrantService = entrantService;
        this.entrantSubjectService = entrantSubjectService;
        this.facultyService = facultyService;
        this.facultySubjectService = facultySubjectService;
        this.adminService = adminService;
        this.applicationService = applicationService;
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
        request.setAttribute("applications", applicationService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public String addFaculty(HttpServletRequest request) {

        request.setAttribute("create", true);

        return "facultyCreate";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public String addFaculty(@ModelAttribute FacultyDto facultyDto, HttpServletRequest request) {

        facultyService.save(facultyDto);

        return superAdminCabinetFaculties(request);
    }

    @RequestMapping(value = "/editFaculty", method = RequestMethod.GET)
    public String editFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {

        Faculty faculty = facultyService.findById(id);

        request.setAttribute("faculty", faculty);
        request.setAttribute("subjects", facultySubjectService.getFacultySubjects(id));
        request.setAttribute("create", false);

        return "facultyCreate";
    }

    @RequestMapping(value = "/editFaculty", method = RequestMethod.POST)
    public String editFaculty(@ModelAttribute FacultyDto facultyDto, HttpServletRequest request) {

        facultyService.edit(facultyDto);
        Faculty faculty = facultyService.findById(facultyDto.getDtoId());
        facultySubjectService.edit(facultyDto, faculty);

        List<Application> applications = applicationService.findByFacultyId(faculty.getId());
        List<Entrant> entrants = new ArrayList<>();
        for (Application a: applications) {
            entrants.add(a.getEntrant());
        }

        applicationService.deleteAll(applications);

        for (Entrant e: entrants) {
            applicationService.save(e, faculty);
        }

        return superAdminCabinetFaculties(request);
    }

    @RequestMapping(value = "/deleteFaculty", method = RequestMethod.GET)
    public String deleteFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {

        facultySubjectService.deleteByFacultyId(id);
        applicationService.deleteByFacultyId(id);
        facultyService.deleteById(id);

        return superAdminCabinetFaculties(request);
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public String addAdmin(HttpServletRequest request) {

        request.setAttribute("create", true);

        return "adminCreate";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute EmailAndPasswordDto emailAndPasswordDto, HttpServletRequest request) {

        adminService.save(emailAndPasswordDto.getEmail(), emailAndPasswordDto.getPassword());

        return superAdminCabinetAdmins(request);
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.GET)
    public String editAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("admin", adminService.findById(id));
        request.setAttribute("create", false);

        return "adminCreate";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editAdmin(@ModelAttribute EmailAndPasswordDto emailAndPasswordDto, HttpServletRequest request) {

        adminService.edit(emailAndPasswordDto.getEmail(), emailAndPasswordDto.getPassword(), emailAndPasswordDto.getDtoId());

        return superAdminCabinetAdmins(request);
    }

    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.GET)
    public String deleteAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        adminService.deleteById(id);

        return superAdminCabinetAdmins(request);
    }
}
