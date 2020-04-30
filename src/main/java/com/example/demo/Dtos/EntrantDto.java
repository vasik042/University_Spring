package com.example.demo.Dtos;

import java.util.Date;

public class EntrantDto {

    private String name;
    private String surname;
    private String dateOfBirth;
    private float schoolGPA;
    private String email;
    private String password;

    private String subjectName2;
    private String subjectName3;
    private String subjectName4;

    private int subjectGrade1;
    private int subjectGrade2;
    private int subjectGrade3;
    private int subjectGrade4;

    public EntrantDto() {
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

    public String getSubjectName2() {
        return subjectName2;
    }

    public void setSubjectName2(String subjectName2) {
        this.subjectName2 = subjectName2;
    }

    public String getSubjectName3() {
        return subjectName3;
    }

    public void setSubjectName3(String subjectName3) {
        this.subjectName3 = subjectName3;
    }

    public String getSubjectName4() {
        return subjectName4;
    }

    public void setSubjectName4(String subjectName4) {
        this.subjectName4 = subjectName4;
    }

    public int getSubjectGrade1() {
        return subjectGrade1;
    }

    public void setSubjectGrade1(int subjectGrade1) {
        this.subjectGrade1 = subjectGrade1;
    }

    public int getSubjectGrade2() {
        return subjectGrade2;
    }

    public void setSubjectGrade2(int subjectGrade2) {
        this.subjectGrade2 = subjectGrade2;
    }

    public int getSubjectGrade3() {
        return subjectGrade3;
    }

    public void setSubjectGrade3(int subjectGrade3) {
        this.subjectGrade3 = subjectGrade3;
    }

    public int getSubjectGrade4() {
        return subjectGrade4;
    }

    public void setSubjectGrade4(int subjectGrade4) {
        this.subjectGrade4 = subjectGrade4;
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
}
