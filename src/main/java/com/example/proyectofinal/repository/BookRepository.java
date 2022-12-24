package com.example.proyectofinal.repository;

import com.example.proyectofinal.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBookByOrderByTitleAsc();
    List<Book> findBookByAuthorContainingIgnoreCase(String author);

}
