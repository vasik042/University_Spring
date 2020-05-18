package com.example.demo.entities;

import com.example.demo.entities.subjects.Coefficient;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private int id;

    private String name;
    private String description;
    private int places;

    @OneToMany(mappedBy = "faculty")
    private Set<Application> applications;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Coefficient> coefficients;

    public Faculty(String name, String description, int places) {
        this.name = name;
        this.description = description;
        this.places = places;
    }

    public Faculty(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Coefficient> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Set<Coefficient> coefficients) {
        this.coefficients = coefficients;
    }
}
