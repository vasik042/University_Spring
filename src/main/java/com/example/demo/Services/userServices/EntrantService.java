package com.example.demo.Services.userServices;

import com.example.demo.Dtos.EntrantDto;
import com.example.demo.Services.subjects.GradeService;
import com.example.demo.Services.subjects.SubjectService;
import com.example.demo.entities.subjects.Grade;
import com.example.demo.entities.subjects.Subject;
import com.example.demo.entities.userEntities.Photo;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.repositories.userRepos.EntrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntrantService {

    private EntrantRepository entrantRepo;
    private GradeService gradeService;
    private SubjectService subjectService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EntrantService(EntrantRepository entrantRepo, GradeService gradeService,
                          SubjectService subjectService, PasswordEncoder passwordEncoder) {
        this.entrantRepo = entrantRepo;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.passwordEncoder = passwordEncoder;
    }

    public void setRoles(String role) {
        List<Entrant> entrants = entrantRepo.findAll();

        for (Entrant entrant: entrants) {
            changeRole(entrant.getId(), role);
        }
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
                passwordEncoder.encode(entrantDto.getPassword()), entrantDto.getEmailHash());
        Photo photo = null;
        try {
            photo = new Photo(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes(), entrant);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entrant.setEntrantPhoto(photo);

        entrant = entrantRepo.save(entrant);

        ArrayList<Grade> grades = new ArrayList<>();

        grades.add(new Grade(entrantDto.getSubjectGrade1(), entrant, subjectService.findByEnglishName("UKRAINIAN")));
        grades.add(new Grade(entrantDto.getSubjectGrade2(), entrant, subjectService.findByEnglishName(entrantDto.getSubjectName2())));
        grades.add(new Grade(entrantDto.getSubjectGrade3(), entrant, subjectService.findByEnglishName(entrantDto.getSubjectName3())));
        Subject sub4 = subjectService.findByEnglishName(entrantDto.getSubjectName4());

        if(!entrantDto.getSubjectGrade4().isEmpty() && sub4 != null){
            grades.add(new Grade(Integer.parseInt(entrantDto.getSubjectGrade4()), entrant, sub4));
        }

        gradeService.saveAll(grades);

        return entrant;
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

    public Entrant findByEmail(String email){
        return entrantRepo.findByEmail(email);
    }

    public Integer findIdByHash(String hash){
        return entrantRepo.findIdByHash(hash);
    }

    public List<Entrant> findAll() {
        return entrantRepo.findAll();
    }
}
