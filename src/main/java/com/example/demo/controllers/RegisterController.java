package com.example.demo.controllers;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Services.EntrantService;
import com.example.demo.Services.MailSenderService;
import com.example.demo.Subjects;
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

    @Autowired
    public RegisterController(EntrantService entrantService, MailSenderService mailSenderService) {
        this.entrantService = entrantService;
        this.mailSenderService = mailSenderService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEntrant(@ModelAttribute EntrantDto entrantDto, HttpServletRequest request, @RequestParam("photo") MultipartFile file) {

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
