package com.example.demo.Services.subjects;

import com.example.demo.Dtos.FacultyDto;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.subjects.Coefficient;
import com.example.demo.entities.subjects.Subject;
import com.example.demo.repositories.subjects.CoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoefficientService {

    private CoefficientRepository coefficientRepo;
    private SubjectService subjectService;

    @Autowired
    public CoefficientService(CoefficientRepository coefficientRepo, SubjectService subjectService) {
        this.coefficientRepo = coefficientRepo;
        this.subjectService = subjectService;
    }

    public List<Coefficient> findAll(){
        return coefficientRepo.findAllOrderBySubjectId();
    }

    public void edit(FacultyDto facultyDto, Faculty faculty) {
        coefficientRepo.deleteByFacultyId(faculty.getId());

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

    public void deleteByFacultyId(int id) {
        coefficientRepo.deleteByFacultyId(id);
    }

    public List<Coefficient> getCoefficients(int id) {
        return coefficientRepo.findByFacultyId(id);
    }
}
