package com.example.demo.entities;

import com.example.demo.entities.userEntities.Entrant;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="application_id")
    private int id;

    @Column(name="priority")
    private int priority;
    @Column(name="GPA")
    private float GPA;

    @ManyToOne
    @JoinColumn(name="entrant_id", nullable=false)
    private Entrant entrant;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable=false)
    private Faculty faculty;

    public Application(float GPA, Entrant entrant, Faculty faculty) {
        this.GPA = GPA;
        this.entrant = entrant;

        int priority = 0;

        for (Application a: entrant.getApplications()) {
            if(a.getPriority() >= priority){
                priority = a.getPriority();
            }
        }

        priority++;
        this.priority = priority;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
