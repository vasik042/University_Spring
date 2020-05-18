package com.example.demo.controllers;

import com.example.demo.Services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private FacultyService facultyService;

    @Autowired
    public IndexController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request) {

        request.setAttribute("faculties", facultyService.findAll());

        return "index";
    }
}
