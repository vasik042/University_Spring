package com.example.demo.repositories;

import com.example.demo.entities.userEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin findByEmailAndPassword(String email, String password);
}
