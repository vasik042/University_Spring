package com.example.demo.controllers;

import com.example.demo.Dtos.EntrantLoginDto;
import com.example.demo.Services.AdminService;
import com.example.demo.Services.EntrantService;
import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private EntrantService entrantService;
    private AdminService adminService;
    private CabinetController cabinetController;

    @Autowired
    public LoginController(EntrantService entrantService, AdminService adminService, CabinetController cabinetController) {
        this.entrantService = entrantService;
        this.adminService = adminService;
        this.cabinetController = cabinetController;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute EntrantLoginDto entrantloginDto, HttpServletRequest request){

        HttpSession session = request.getSession(true);

        Entrant entrant = entrantService.findByEmailAndPassword(entrantloginDto.getEmail(), entrantloginDto.getPassword());

        if (entrant == null){
            Admin admin = adminService.findByEmailAndPassword(entrantloginDto.getEmail(), entrantloginDto.getPassword());

            if (admin != null){
                session.setAttribute("UserId", admin.getId());
                session.setAttribute("role", admin.getRole());

                return cabinetController.getCabinet(request);
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

                return cabinetController.getCabinet(request);
            }
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        session.setAttribute("UserId", null);
        session.setAttribute("role", null);

        return "index";
    }
}
