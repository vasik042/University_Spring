package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    int id;

    @Column(name = "name")
    String name;
    @Column(name = "places")
    int places;

    @OneToMany(mappedBy = "faculty")
    Set<Application> applications;

    @OneToMany(mappedBy = "faculty")
    Set<FacultySubject> facultySubjects;

    public Faculty(String name, int places) {
        this.name = name;
        this.places = places;
    }

    public Faculty(){}

    public void setNecessarySubjects(String firstSub, float firstCoef,
                                     String secondSub, float secondCoef,
                                     String thirdSub, float thirdCoef){
        facultySubjects = new HashSet<>();
        facultySubjects.add(new FacultySubject(firstSub, firstCoef, this));
        facultySubjects.add(new FacultySubject(secondSub, secondCoef, this));
        facultySubjects.add(new FacultySubject(thirdSub, thirdCoef, this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<FacultySubject> getFacultySubjects() {
        return facultySubjects;
    }

    public void setFacultySubjects(Set<FacultySubject> facultySubjects) {
        this.facultySubjects = facultySubjects;
    }
}
