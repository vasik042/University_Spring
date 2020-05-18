package com.example.demo.Services.userServices;

import com.example.demo.entities.userEntities.Photo;
import com.example.demo.repositories.userRepos.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Photo> findAll() {
        return photoRepo.findAll();
    }
}
