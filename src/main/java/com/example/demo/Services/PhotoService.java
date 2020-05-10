package com.example.demo.Services;

import com.example.demo.entities.Photo;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    private PhotoRepository photoRepo;

    @Autowired
    public PhotoService(PhotoRepository photoRepo) {
        this.photoRepo = photoRepo;
    }

    public Photo findByEntrantId(int id){
        return photoRepo.findByEntrantId(id);
    }

    public void save(Photo photo) {
        photoRepo.save(photo);
    }
}
