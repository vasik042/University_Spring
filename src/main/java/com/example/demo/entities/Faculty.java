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

    String name;
    String description;
    int places;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    Set<Application> applications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    Set<FacultySubject> facultySubjects;

    public Faculty(String name, String description, int places) {
        this.name = name;
        this.description = description;
        this.places = places;
    }

    public Faculty(){}

    public void setNecessarySubjects(FacultySubject fs1, FacultySubject fs2, FacultySubject fs3, FacultySubject fs4) {
        facultySubjects = new HashSet<>();
        facultySubjects.add(fs1);
        facultySubjects.add(fs2);
        facultySubjects.add(fs3);
        if (fs4 != null){
            facultySubjects.add(fs4);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
