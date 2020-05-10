package com.example.demo.controllers;

import com.example.demo.Services.PhotoService;
import com.example.demo.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/getPhoto", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadPhoto(@RequestParam(name = "id") int id, HttpServletRequest request) {

        Photo photo = photoService.findByEntrantId(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + photo.getFileName())
                .body(new ByteArrayResource(photo.getData()));
    }
}
