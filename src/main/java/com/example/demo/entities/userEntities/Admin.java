package com.example.demo.entities.userEntities;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    int id;

    String email;
    String password;
    String role;

    public Admin(String email, String password){
        this.email = email;
        this.password = password;
        this.role = Roles.ADMIN.name();
    }

    public Admin(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
