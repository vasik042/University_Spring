package com.example.demo.entities.subjects;

import com.example.demo.entities.Faculty;

import javax.persistence.*;

@Entity
@Table(name = "faculty_coefficient")
public class Coefficient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="coefficient")
    private float coefficient;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable=false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
    private Subject subject;

    public Coefficient(float coef, Faculty faculty, Subject subject) {
        this.coefficient = coef;
        this.faculty = faculty;
        this.subject = subject;
    }

    public Coefficient(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
