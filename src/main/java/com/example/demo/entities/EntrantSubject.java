package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "entrant_subject")
public class EntrantSubject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name="subject_name")
    String subjectName;
    @Column(name="grade")
    int grade;

    @ManyToOne
    @JoinColumn(name="entrant_id", nullable=false)
    Entrant entrant;

    public EntrantSubject(String subjectName, int grade, Entrant entrant) {
        this.subjectName = subjectName;
        this.grade = grade;
        this.entrant = entrant;
    }

    public EntrantSubject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
}
