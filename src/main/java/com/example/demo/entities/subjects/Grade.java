package com.example.demo.entities.subjects;

import com.example.demo.entities.userEntities.Entrant;

import javax.persistence.*;

@Entity
@Table(name = "entrant_grade")
public class Grade {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="grade")
    private int grade;

    @ManyToOne
    @JoinColumn(name="entrant_id", nullable=false)
    private Entrant entrant;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
    private Subject subject;

    public Grade(int grade, Entrant entrant, Subject subject) {
        this.grade = grade;
        this.entrant = entrant;
        this.subject = subject;
    }

    public Grade(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Entrant getEntrant() {
        return entrant;
    }

    public void setEntrant(Entrant entrant) {
        this.entrant = entrant;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
