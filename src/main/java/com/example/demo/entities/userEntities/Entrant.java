package com.example.demo.entities.userEntities;

import com.example.demo.entities.Application;
import com.example.demo.entities.EntrantSubject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entrant")
public class Entrant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrant_id")
    int id;

    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    String email;
    String password;
    String role;
    @Column(name = "date_of_birth")
    String dateOfBirth;
    @Column(name = "school_GPA")
    float schoolGPA;
    @Column(name = "applications_left")
    int applicationsLeft;

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
        this.role = Roles.NOT_VERIFIER_ENTRANT.name();
        this.applicationsLeft = 3;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public int getApplicationsLeft() {
        return applicationsLeft;
    }

    public void setApplicationsLeft(int applicationsLeft) {
        this.applicationsLeft = applicationsLeft;
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", schoolGPA=" + schoolGPA +
                ", applicationsLeft=" + applicationsLeft +
                ", subjects=" + subjects +
                ", applications=" + applications +
                '}';
    }
}
