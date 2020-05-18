package com.example.demo.controllers;

import com.example.demo.Dtos.AdminDto;
import com.example.demo.Dtos.FacultyDto;
import com.example.demo.Services.*;
import com.example.demo.Services.subjects.CoefficientService;
import com.example.demo.Services.subjects.GradeService;
import com.example.demo.Services.userServices.AdminService;
import com.example.demo.Services.userServices.EntrantService;
import com.example.demo.Services.userServices.MailSenderService;
import com.example.demo.Services.userServices.TheEndService;
import com.example.demo.entities.Application;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/superAdminCabinet")
public class SuperAdminController {

    private EntrantService entrantService;
    private GradeService gradeService;
    private FacultyService facultyService;
    private CoefficientService coefficientService;
    private AdminService adminService;
    private ApplicationService applicationService;
    private TheEndService theEndService;
    private PasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;

    @Autowired
    public SuperAdminController(EntrantService entrantService,
                                GradeService gradeService,
                                FacultyService facultyService,
                                CoefficientService coefficientService,
                                AdminService adminService,
                                ApplicationService applicationService,
                                TheEndService theEndService,
                                PasswordEncoder passwordEncoder,
                                MailSenderService mailSenderService) {
        this.entrantService = entrantService;
        this.gradeService = gradeService;
        this.facultyService = facultyService;
        this.coefficientService = coefficientService;
        this.adminService = adminService;
        this.applicationService = applicationService;
        this.theEndService = theEndService;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
    }

    @RequestMapping(value = "/Faculties", method = RequestMethod.GET)
    public String superAdminCabinetFaculties(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        request.setAttribute("choose", "faculties");
        request.setAttribute("coefs", coefficientService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/Admins", method = RequestMethod.GET)
    public String superAdminCabinetAdmins(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        request.setAttribute("choose", "admins");
        request.setAttribute("admins", adminService.findAll());

        return "superAdminCabinet";
    }

    @RequestMapping(value = "/Entrants", method = RequestMethod.GET)
    public String superAdminCabinetEntrants(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        request.setAttribute("choose", "entrants");
        request.setAttribute("grades", gradeService.findAll());
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
        request.setAttribute("coefs", coefficientService.getCoefficients(id));
        request.setAttribute("create", false);

        return "facultyCreate";
    }

    @RequestMapping(value = "/editFaculty", method = RequestMethod.POST)
    public String editFaculty(@ModelAttribute FacultyDto facultyDto, HttpServletRequest request) {

        facultyService.edit(facultyDto);
        Faculty faculty = facultyService.findById(facultyDto.getDtoId());
        coefficientService.edit(facultyDto, faculty);

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

        coefficientService.deleteByFacultyId(id);
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
    public String addAdmin(@ModelAttribute AdminDto adminDto, HttpServletRequest request) {

        adminService.save(adminDto.getEmail(), passwordEncoder.encode(adminDto.getPassword()));

        return superAdminCabinetAdmins(request);
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.GET)
    public String editAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        request.setAttribute("admin", adminService.findById(id));
        request.setAttribute("create", false);

        return "adminCreate";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editAdmin(@ModelAttribute AdminDto adminDto, HttpServletRequest request) {

        adminService.edit(adminDto.getEmail(), passwordEncoder.encode(adminDto.getPassword()), adminDto.getDtoId());

        return superAdminCabinetAdmins(request);
    }

    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.GET)
    public String deleteAdmin(@RequestParam(name = "id") int id, HttpServletRequest request) {

        adminService.deleteById(id);

        return superAdminCabinetAdmins(request);
    }

    @RequestMapping(value = "/theEnd", method = RequestMethod.GET)
    public String theEnd(HttpServletRequest request) {

        theEndService.theEnd();

        return superAdminCabinetFaculties(request);
    }
}
