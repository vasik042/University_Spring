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
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "role")
    int role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entrant")
    Set<EntrantSubject> subjects;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entrant")
    Set<Application> applications;

    public Entrant(String name, String surname, String dateOfBirth, float schoolGPA, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.schoolGPA = schoolGPA;
        this.email = email;
        this.password = password;
        this.role = 0;
    }

    public Entrant(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = -1;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
