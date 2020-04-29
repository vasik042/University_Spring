package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entrant")
public class Entrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrant_id")
    int id;

    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "date_of_birth")
    String dateOfBirth;
    @Column(name = "school_GPA")
    float schoolGPA;

    @OneToMany(mappedBy = "entrant")
    Set<EntrantSubject> subjects;

    @OneToMany(mappedBy = "entrant")
    Set<Application> applications;

    public Entrant(String name, String surname, String dateOfBirth, float schoolGPA) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.schoolGPA = schoolGPA;
    }

    public Entrant(){}

    public void setSubjects(EntrantSubject es1, EntrantSubject es2, EntrantSubject es3, EntrantSubject es4) {
        subjects = new HashSet<>();
        subjects.add(es1);
        subjects.add(es2);
        subjects.add(es3);
        if(es4 != null){
            subjects.add(es4);
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getSchoolGPA() {
        return schoolGPA;
    }

    public void setSchoolGPA(float schoolGPA) {
        this.schoolGPA = schoolGPA;
    }

    public Set<EntrantSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<EntrantSubject> subjects) {
        this.subjects = subjects;
    }


}
