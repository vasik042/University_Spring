package com.example.demo.repositories;

import com.example.demo.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{

    public Photo findByEntrantId(int id);
}
