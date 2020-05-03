package com.example.demo.repositories;

import com.example.demo.entities.userEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    @Query(value = "SELECT admin_id FROM admin WHERE email = ?1 AND password = ?2", nativeQuery = true)
    Integer findIdByEmailAndPassword(String email, String password);

}
