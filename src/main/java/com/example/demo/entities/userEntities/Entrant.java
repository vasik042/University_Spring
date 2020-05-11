package com.example.demo.entities.userEntities;

import com.example.demo.entities.Application;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.Photo;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "entrant")
public class Entrant{

    @Id
    @Column(name = "entrant_id", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = {@Parameter(name = "property", value = "entrantPhoto")})
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    private String email;
    private String password;
    private String role;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "school_GPA")
    private float schoolGPA;
    @Column(name = "applications_left")
    private int applicationsLeft;
    @Column(name = "email_verify_hash")
    private String emailHash;

    @OneToMany(mappedBy = "entrant")
    Set<EntrantSubject> subjects;

    @OneToMany(mappedBy = "entrant")
    Set<Application> applications;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Photo entrantPhoto;

    public Entrant(String name, String surname, String dateOfBirth, float schoolGPA,
                   String email, String password, String emailHash) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.schoolGPA = schoolGPA;
        this.email = email;
        this.password = password;
        this.emailHash = emailHash;
        this.role = Roles.NOT_VERIFIED_EMAIL_ENTRANT.name();
        this.applicationsLeft = 3;
    }

    public Entrant(){}

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

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public Photo getEntrantPhoto() {
        return entrantPhoto;
    }

    public void setEntrantPhoto(Photo entrantPhoto) {
        this.entrantPhoto = entrantPhoto;
    }
}
