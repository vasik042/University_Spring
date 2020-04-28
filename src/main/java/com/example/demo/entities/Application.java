package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="application_id")
    int id;

    @Column(name="GPA")
    float GPA;

    @ManyToOne
    @JoinColumn(name="entrant_id", nullable=false)
    Entrant entrant;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable=false)
    Faculty faculty;

    public Application(float GPA, Entrant entrant, Faculty faculty) {
        this.GPA = GPA;
        this.entrant = entrant;
        this.faculty = faculty;
    }

    public Application(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public Entrant getEntrant() {
        return entrant;
    }

    public void setEntrant(Entrant entrant) {
        this.entrant = entrant;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
