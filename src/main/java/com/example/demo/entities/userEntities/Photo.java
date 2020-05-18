package com.example.demo.entities.userEntities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_type")
    private String fileType;
    @Lob
    private byte[] data;

    @OneToOne(mappedBy = "entrantPhoto")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Entrant entrant;

    public Photo(String fileName, String fileType, byte[] data, Entrant entrant) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.entrant = entrant;
    }

    public Photo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Entrant getEntrant() {
        return entrant;
    }

    public void setEntrant(Entrant entrant) {
        this.entrant = entrant;
    }
}
