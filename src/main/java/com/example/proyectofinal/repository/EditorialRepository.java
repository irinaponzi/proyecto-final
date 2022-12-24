package com.example.proyectofinal.repository;

import com.example.proyectofinal.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long> {

    Editorial findEditorialByNameContainingIgnoreCase(String name);

}
