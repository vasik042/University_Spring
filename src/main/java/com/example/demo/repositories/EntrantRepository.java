package com.example.demo.repositories;

import com.example.demo.entities.Entrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Repository
public interface EntrantRepository  extends JpaRepository<Entrant, Integer> {

    @Query(value = "SELECT entrant_id FROM entrant WHERE email = ?1 AND password = ?2", nativeQuery = true)
    public Integer findIdByEmailAndPassword(String email, String password);

    @Query(value = "SELECT role FROM entrant WHERE email = ?1 AND password = ?2", nativeQuery = true)
    public Integer findRoleByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM entrant WHERE role = 0", nativeQuery = true)
    public List<Entrant> findByRole();

    @Transactional
    @Modifying
    @Query(value = "UPDATE entrant SET role = ?1 WHERE entrant_id = ?2", nativeQuery = true)
    void changeRole(int role, int id);
}
