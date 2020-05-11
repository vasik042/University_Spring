package com.example.demo.Dtos;

public class FacultyDto {
    private String name;
    private String description;
    private int places;
    private int dtoId;

    private String subjectName2;
    private String subjectName3;
    private String subjectName4;

    private float subjectCoef1;
    private float subjectCoef2;
    private float subjectCoef3;
    private String subjectCoef4;

    FacultyDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
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

    public float getSubjectCoef1() {
        return subjectCoef1;
    }

    public void setSubjectCoef1(float subjectCoef1) {
        this.subjectCoef1 = subjectCoef1;
    }

    public float getSubjectCoef2() {
        return subjectCoef2;
    }

    public void setSubjectCoef2(float subjectCoef2) {
        this.subjectCoef2 = subjectCoef2;
    }

    public float getSubjectCoef3() {
        return subjectCoef3;
    }

    public void setSubjectCoef3(float subjectCoef3) {
        this.subjectCoef3 = subjectCoef3;
    }

    public String getSubjectCoef4() {
        return subjectCoef4;
    }

    public void setSubjectCoef4(String subjectCoef4) {
        this.subjectCoef4 = subjectCoef4;
    }

    public int getDtoId() {
        return dtoId;
    }

    public void setDtoId(int dtoId) {
        this.dtoId = dtoId;
    }
}
