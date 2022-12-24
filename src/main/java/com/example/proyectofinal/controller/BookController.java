package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.RespBookDto;
import com.example.proyectofinal.dto.RespMessageDto;
import com.example.proyectofinal.service.BookService;
import com.example.proyectofinal.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    IBookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/findBookById/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    @GetMapping("/findBooksByName")
    public ResponseEntity<List<BookDto>> findBookByOrderByTitleAsc() {
        return new ResponseEntity<>(bookService.findBookByOrderByTitleAsc(), HttpStatus.OK);
    }

    @GetMapping("/findBookByAuthor/{author}")
    public ResponseEntity<List<BookDto>> findBookByAuthor(@PathVariable String author) {
        return new ResponseEntity<>(bookService.findBookByAuthor(author), HttpStatus.OK);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<RespBookDto> updateBookStockById(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBookById(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<RespMessageDto> deleteBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBookById(id), HttpStatus.OK);
    }

}
