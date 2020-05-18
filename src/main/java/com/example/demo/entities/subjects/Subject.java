package com.example.demo.entities.subjects;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="subject_id")
    private int id;

    @Column(name="ukrainian_name")
    private String ukrainianName;
    @Column(name="english_name")
    private String englishName;

    @OneToMany(mappedBy = "subject")
    private Set<Grade> grade;

    @OneToMany(mappedBy = "subject")
    private Set<Coefficient> coefficient;

    public Subject(String urkName, String engName) {
        this.ukrainianName = urkName;
        this.englishName = engName;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public void setUkrainianName(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Set<Grade> getGrade() {
        return grade;
    }

    public void setGrade(Set<Grade> grade) {
        this.grade = grade;
    }

    public Set<Coefficient> getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Set<Coefficient> coefficient) {
        this.coefficient = coefficient;
    }
}
