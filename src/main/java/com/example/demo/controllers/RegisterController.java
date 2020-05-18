package com.example.demo.controllers;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Services.userServices.EntrantService;
import com.example.demo.Services.userServices.MailSenderService;
import com.example.demo.Services.subjects.SubjectService;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class RegisterController {

    private EntrantService entrantService;
    private MailSenderService mailSenderService;
    private SubjectService subjectService;

    @Autowired
    public RegisterController(EntrantService entrantService, MailSenderService mailSenderService,
                              SubjectService subjectService) {
        this.entrantService = entrantService;
        this.mailSenderService = mailSenderService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(HttpServletRequest request) {

        String role = entrantService.findAll().get(0).getRole();

        if(role.equals(Roles.NOT_PAST.name()) || role.equals(Roles.PAST.name())){
            request.setAttribute("end", true);
            return "index";
        }else {
            return "register";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEntrant(@ModelAttribute EntrantDto entrantDto, HttpServletRequest request, @RequestParam("photo") MultipartFile file) {

        boolean sec = false;
        boolean thr = false;

        if (subjectService.findByEnglishName(entrantDto.getSubjectName2()) != null){
            sec = true;
        }
        if(subjectService.findByEnglishName(entrantDto.getSubjectName3()) != null) {
            thr = true;
        }

        if (sec && thr && ((!entrantDto.getSubjectName4().equals(entrantDto.getSubjectName2()))
                || (!entrantDto.getSubjectName4().equals(entrantDto.getSubjectName3())))){
            String uuid = UUID.randomUUID().toString();

            entrantDto.setEmailHash(uuid);
            entrantService.save(entrantDto, file);

            mailSenderService.sendVerificationEmail(entrantDto.getEmail(), uuid);

            request.setAttribute("verified", false);

            return "emailVerifiedPage";
        }else {
            return "register";
        }
    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam(name = "hash") String hash, HttpServletRequest request){

        Integer id = entrantService.findIdByHash(hash);

        if (id != null){
            entrantService.changeRole(id, Roles.NOT_VERIFIED_ENTRANT.name());

            request.setAttribute("verified", true);

            return "emailVerifiedPage";
        }else {
            return "404";
        }
    }
}
