package com.example.demo.repositories;

import com.example.demo.entities.userEntities.Entrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EntrantRepository  extends JpaRepository<Entrant, Integer> {

    public Entrant findByEmailAndPassword(String email, String password);

    public List<Entrant> findByRole(String role);

    @Query(value = "SELECT email FROM entrant WHERE entrant_id = ?1", nativeQuery = true)
    public String findEmailById(int id);

    @Query(value = "SELECT entrant_id FROM entrant WHERE email_verify_hash = ?1", nativeQuery = true)
    public Integer findIdByHash(String hash);

    @Transactional
    @Modifying
    @Query(value = "UPDATE entrant SET role = ?1 WHERE entrant_id = ?2", nativeQuery = true)
    void changeRole(String role, int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE entrant SET applications_left = ?1 WHERE entrant_id = ?2", nativeQuery = true)
    void changeApplicationsLeft(int applications, int id);
}
