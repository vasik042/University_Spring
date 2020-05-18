package com.example.demo.Services;

import com.example.demo.Dtos.FacultyDto;
import com.example.demo.Services.subjects.SubjectService;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.subjects.Coefficient;
import com.example.demo.entities.subjects.Subject;
import com.example.demo.repositories.FacultyRepository;
import com.example.demo.repositories.subjects.CoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepo;
    private CoefficientRepository coefficientRepo;
    private SubjectService subjectService;

    @Autowired
    public FacultyService(FacultyRepository facultyRepo, CoefficientRepository coefficientRepo,
                          SubjectService subjectService) {
        this.coefficientRepo = coefficientRepo;
        this.facultyRepo = facultyRepo;
        this.subjectService = subjectService;
    }

    public List<Faculty> findAll(){
        return facultyRepo.findAll();
    }

    public Faculty findById(int id){
        return facultyRepo.findById(id).get();
    }

    public void edit(FacultyDto facultyDto) {
        facultyRepo.edit(facultyDto.getName(), facultyDto.getPlaces(), facultyDto.getDescription(), facultyDto.getDtoId());
    }

    public void save(FacultyDto facultyDto) {
        Faculty faculty = facultyRepo.save(new Faculty(facultyDto.getName(), facultyDto.getDescription(), facultyDto.getPlaces()));

        ArrayList<Coefficient> coefficients = new ArrayList<>();

        coefficients.add(new Coefficient(facultyDto.getSubjectCoef1(), faculty, subjectService.findByEnglishName("UKRAINIAN")));
        coefficients.add(new Coefficient(facultyDto.getSubjectCoef2(), faculty, subjectService.findByEnglishName(facultyDto.getSubjectName2())));
        coefficients.add(new Coefficient(facultyDto.getSubjectCoef3(), faculty, subjectService.findByEnglishName(facultyDto.getSubjectName3())));
        Subject sub4 = subjectService.findByEnglishName(facultyDto.getSubjectName4());

        if(!facultyDto.getSubjectCoef4().isEmpty() && sub4 != null){
            coefficients.add(new Coefficient(Float.parseFloat(facultyDto.getSubjectCoef4()), faculty, sub4));
        }

        coefficientRepo.saveAll(coefficients);
    }

    public void deleteById(int id) {
        facultyRepo.deleteById(id);
    }
}
