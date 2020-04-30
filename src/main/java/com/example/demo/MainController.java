package com.example.demo;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Dtos.EntrantLoginDto;
import com.example.demo.Services.*;
import com.example.demo.Subjects;
import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private EntrantService entrantService;
    @Autowired
    private EntrantSubjectService entrantSubjectService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultySubjectService facultySubjectService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request) {
        request.setAttribute("faculties", facultyService.findAll());
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(HttpServletRequest request) {

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEntrant(@ModelAttribute EntrantDto entrantDto, HttpServletRequest request) {
        boolean sec = false;
        boolean thr = false;
        for (Subjects s: Subjects.values()) {
            if (s.name().equals(entrantDto.getSubjectName2())){
                sec = true;
            }else if(s.name().equals(entrantDto.getSubjectName3())) {
                thr = true;
            }
        }
        if (sec && thr && ((!entrantDto.getSubjectName4().equals(entrantDto.getSubjectName2()))
                || (!entrantDto.getSubjectName4().equals(entrantDto.getSubjectName3())))){
            Entrant saved = entrantService.save(entrantDto);
            return "login";
        }else {
            return "register";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute EntrantLoginDto entrantloginDto, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Integer id = entrantService.findIdByEmailAndPassword(entrantloginDto.getEmail(), entrantloginDto.getPassword());
        Integer role = entrantService.findRoleByEmailAndPassword(entrantloginDto.getEmail(), entrantloginDto.getPassword());
        if(id != null){
            session.setAttribute("UserId", id);
            session.setAttribute("role", role);
            return getCabinet(request);
        }else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("UserId", null);
        session.setAttribute("role", null);
        return getIndex(request);
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String getFaculty(@RequestParam(name = "id") int id, HttpServletRequest request) {
        request.setAttribute("entrants", applicationService.findByFacultyId(id));
        request.setAttribute("faculties", facultyService.findAll());
        request.setAttribute("facultyId", id);
        Integer role = (Integer)request.getSession().getAttribute("role");
        if (role != null){
            if (role == 0){
                request.setAttribute("canReg", 1);
            }else if (role >= 4){
                request.setAttribute("canReg", 2);
            }else if(role > 0 ){
                int i = 0;
                List<FacultySubject> facultySubjects = facultySubjectService.getFacultySubjects(id);
                List<EntrantSubject> entrantSubjects = entrantSubjectService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));
                for (FacultySubject fs: facultySubjects) {
                    for (EntrantSubject es: entrantSubjects) {
                        if(fs.getSubjectName().equals(es.getSubjectName())){
                            i++;
                        }
                    }
                }
                if(i == facultySubjects.size()){
                    request.setAttribute("canReg", 0);
                }else {
                    request.setAttribute("canReg", 4);
                }
                List<Application> applications = applicationService.findByEntrantId((Integer) request.getSession().getAttribute("UserId"));
                for (Application a : applications) {
                    if (a.getFacultySavedId() == id){
                        request.setAttribute("canReg", 3);
                    }
                }
            }
        }
        return "faculty";
    }

    @RequestMapping(value = "/makeApplication", method = RequestMethod.GET)
    public String makeApplication(@RequestParam(name = "id") int id, HttpServletRequest request){

        Entrant entrant = entrantService.findById((Integer) request.getSession().getAttribute("UserId"));
        List<Application> applications = applicationService.findByEntrantId(entrant.getId());
        for (Application a: applications) {
            if(a.getFacultySavedId() == id){
                return getFaculty(id, request);
            }
        }
        Faculty faculty = facultyService.findById(id);

        applicationService.save(entrant, faculty);

        entrantService.changeRole(entrant.getId(), entrant.getRole()+1);

        return getFaculty(id, request);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activate(@RequestParam(name = "id") int id, HttpServletRequest request) {
        entrantService.changeRole(id, 1);
        return getCabinet(request);
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String getCabinet(HttpServletRequest request) {
        if((Integer)request.getSession().getAttribute("role") >= 0){
            request.setAttribute("applications", applicationService.findByEntrantId((Integer)request.getSession().getAttribute("UserId")));
        }else if ((Integer)request.getSession().getAttribute("role") < 0){
            List<Entrant> entrants = entrantService.findByRole();
            request.setAttribute("entrants", entrants);
            List<EntrantSubject> subjects = new ArrayList<>();
            for (Entrant e:entrants) {
                subjects.addAll(entrantSubjectService.findByEntrantId(e.getId()));
            }
            request.setAttribute("subjects", subjects);
        }
        request.setAttribute("faculties", facultyService.findAll());
        return "cabinet";
    }

}
