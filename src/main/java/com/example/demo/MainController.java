package com.example.demo;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Services.EntrantService;
import com.example.demo.Services.FacultySubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class MainController {

    @Autowired
    private EntrantService entrantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCabinet() {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getCreate(HttpServletRequest request) {

        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createStudent(@ModelAttribute EntrantDto entrantDto) {
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
            entrantService.save(entrantDto);
            return "index";
        }else {
            return "register";
        }
    }
}
