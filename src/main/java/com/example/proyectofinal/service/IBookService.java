package com.example.proyectofinal.service;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.RespBookDto;
import com.example.proyectofinal.dto.RespMessageDto;

import java.util.List;

public interface IBookService {

    List<BookDto> findAllBooks();
    BookDto findBookById(Long id);
    List<BookDto> findBookByOrderByTitleAsc();
    List<BookDto> findBookByAuthor(String author);
    RespBookDto updateBookById(Long id, BookDto bookDto);
    RespMessageDto deleteBookById(Long id);

}
