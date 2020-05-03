package com.example.demo.controllers;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Services.EntrantService;
import com.example.demo.Subjects;
import com.example.demo.entities.userEntities.Entrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private EntrantService entrantService;

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
}
