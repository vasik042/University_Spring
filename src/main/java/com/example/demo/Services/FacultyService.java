package com.example.demo.Services;

import com.example.demo.Dtos.FacultyDto;
import com.example.demo.Subjects;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.FacultySubject;
import com.example.demo.repositories.FacultyRepository;
import com.example.demo.repositories.FacultySubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepo;
    private FacultySubjectRepository facultySubjectRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepo, FacultySubjectRepository facultySubjectRepository) {
        this.facultySubjectRepository = facultySubjectRepository;
        this.facultyRepo = facultyRepo;
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

        ArrayList<FacultySubject> subjects = new ArrayList<>();

        subjects.add(new FacultySubject(Subjects.UKRAINIAN.name(), facultyDto.getSubjectCoef1(), faculty));
        subjects.add(new FacultySubject(facultyDto.getSubjectName2(), facultyDto.getSubjectCoef2(), faculty));
        subjects.add(new FacultySubject(facultyDto.getSubjectName3(), facultyDto.getSubjectCoef3(), faculty));

        if(!facultyDto.getSubjectCoef4().isEmpty()) {
            for (Subjects s : Subjects.values()) {
                if (s.name().equals(facultyDto.getSubjectName4())) {
                    subjects.add(new FacultySubject(facultyDto.getSubjectName4(), Float.parseFloat(facultyDto.getSubjectCoef4()), faculty));
                }
            }
        }

        facultySubjectRepository.saveAll(subjects);
    }

    public void deleteById(int id) {
        facultyRepo.deleteById(id);
    }
}
