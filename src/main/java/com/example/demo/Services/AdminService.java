package com.example.demo.Services;

import com.example.demo.entities.userEntities.Admin;
import com.example.demo.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepo adminRepo;

    @Autowired
    public AdminService(AdminRepo adminRepo){
        this.adminRepo = adminRepo;
    }

    public void save(Admin admin){
        adminRepo.save(admin);
    }

    public Integer findIdByEmailAndPassword(String email, String password){
        return adminRepo.findIdByEmailAndPassword(email, password);
    }
}
