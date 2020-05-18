package com.example.demo.repositories.userRepos;

import com.example.demo.entities.userEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE admin SET" +
            " email = ?1," +
            " password = ?2" +
            " WHERE admin_id = ?3", nativeQuery = true)
    void edit(String email, String password, int id);

    Admin findByEmail(String username);
}
