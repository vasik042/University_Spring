package com.example.demo.repositories;

import com.example.demo.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{

    Photo findByEntrantId(int id);
}
