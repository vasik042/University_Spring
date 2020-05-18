package com.example.demo.repositories;

import com.example.demo.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, Integer>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE faculty SET" +
            " name = ?1," +
            " places = ?2," +
            " description = ?3" +
            " WHERE faculty_id = ?4", nativeQuery = true)
    void edit(String name, int places, String description, int id);
}
