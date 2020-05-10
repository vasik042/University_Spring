package com.example.demo.Services;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Subjects;
import com.example.demo.entities.Photo;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.repositories.EntrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EntrantService {

    private EntrantRepository entrantRepo;
    private EntrantSubjectService entrantSubjectService;
    private PhotoService photoService;

    @Autowired
    public EntrantService(EntrantRepository entrantRepo, EntrantSubjectService entrantSubjectService, PhotoService photoService) {
        this.entrantRepo = entrantRepo;
        this.entrantSubjectService = entrantSubjectService;
        this.photoService = photoService;
    }

    public void deleteById(int id){
        entrantRepo.deleteById(id);
    }

    public Entrant findById(int id){
        return entrantRepo.findById(id).get();
    }

    public Entrant save(EntrantDto entrantDto, MultipartFile file) {
        Entrant entrant = new Entrant(entrantDto.getName(), entrantDto.getSurname(),
                entrantDto.getDateOfBirth(), entrantDto.getSchoolGPA(), entrantDto.getEmail(),
                entrantDto.getPassword(), entrantDto.getEmailHash());
        Photo photo = null;
        try {
            photo = new Photo(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes(), entrant);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entrant.setEntrantPhoto(photo);

        entrant = entrantRepo.save(entrant);

        EntrantSubject sub1 = new EntrantSubject(Subjects.UKRAINIAN.name(), entrantDto.getSubjectGrade1(), entrant);
        EntrantSubject sub2 = new EntrantSubject(entrantDto.getSubjectName2(), entrantDto.getSubjectGrade2(), entrant);
        EntrantSubject sub3 = new EntrantSubject(entrantDto.getSubjectName3(), entrantDto.getSubjectGrade3(), entrant);
        EntrantSubject sub4 = null;
        for (Subjects s: Subjects.values()) {
            if (s.name().equals(entrantDto.getSubjectName4())) {
                sub4 = new EntrantSubject(entrantDto.getSubjectName4(), entrantDto.getSubjectGrade4(), entrant);
            }
        }

        entrantSubjectService.save(sub1);
        entrantSubjectService.save(sub2);
        entrantSubjectService.save(sub3);
        if(sub4 != null){
            entrantSubjectService.save(sub4);
        }
        return entrant;
    }

    public Entrant findByEmailAndPassword(String email, String password){
        return entrantRepo.findByEmailAndPassword(email, password);
    }

    public List<Entrant> findByRole(String role){
        return entrantRepo.findByRole(role);
    }

    public void changeRole(int id, String role){
        entrantRepo.changeRole(role, id);
    }

    public void changeApplicationsLeft(int id, int applications){
        entrantRepo.changeApplicationsLeft(applications, id);
    }

    public String findEmailById(int id){
        return entrantRepo.findEmailById(id);
    }

    public Integer findIdByHash(String hash){
        return entrantRepo.findIdByHash(hash);
    }

    public List<Entrant> findAll() {
        return entrantRepo.findAll();
    }
}
