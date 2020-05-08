package com.example.demo.Services;

import com.example.demo.Dtos.FacultyDto;
import com.example.demo.Subjects;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.FacultySubject;
import com.example.demo.repositories.FacultySubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultySubjectService {

    private FacultySubjectRepository facultySubjectRepo;

    @Autowired
    public FacultySubjectService(FacultySubjectRepository facultySubjectRepo) {
        this.facultySubjectRepo = facultySubjectRepo;
    }

    public List<FacultySubject> findAll(){
        return facultySubjectRepo.findAll();
    }

    public List<FacultySubject> getFacultySubjects(int id){
        return facultySubjectRepo.findByFacultyId(id);
    }

    public void edit(FacultyDto facultyDto, Faculty faculty) {
        facultySubjectRepo.deleteByFacultyId(faculty.getId());

        ArrayList<FacultySubject> subjects = new ArrayList<>();

        subjects.add(new FacultySubject(Subjects.UKRAINIAN.name(), facultyDto.getSubjectCoef1(), faculty));
        subjects.add(new FacultySubject(facultyDto.getSubjectName2(), facultyDto.getSubjectCoef2(), faculty));
        subjects.add(new FacultySubject(facultyDto.getSubjectName3(), facultyDto.getSubjectCoef3(), faculty));

        for (Subjects s: Subjects.values()) {
            if (s.name().equals(facultyDto.getSubjectName4())) {
                subjects.add(new FacultySubject(facultyDto.getSubjectName4(), facultyDto.getSubjectCoef4(), faculty));
            }
        }

        facultySubjectRepo.saveAll(subjects);
    }
}
