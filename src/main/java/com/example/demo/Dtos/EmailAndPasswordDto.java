package com.example.demo.Dtos;

public class EmailAndPasswordDto {

    private String email;
    private String password;
    private int dtoId;

    public EmailAndPasswordDto(){}

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

    public int getDtoId() {
        return dtoId;
    }

    public void setDtoId(int dtoId) {
        this.dtoId = dtoId;
    }
}
