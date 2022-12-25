package com.example.proyectofinal.utils;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.EditorialDto;
import com.example.proyectofinal.dto.EmailDto;
import com.example.proyectofinal.entity.Book;
import com.example.proyectofinal.entity.Editorial;
import com.example.proyectofinal.entity.Email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    // Para EditorialServiceTest
    public static List<EditorialDto> loadEditorialsDto() {

        List<EditorialDto> editorialDtoList = new ArrayList<>();
        Set<BookDto> bookDtoList1 = new HashSet<>();
        Set<BookDto> bookDtoList2 = new HashSet<>();

        EmailDto emailDto1 = new EmailDto(1L, "infominotauro@planeta.es");
        EmailDto emailDto2 = new EmailDto(2L, "argentina@editorialivrea.com");

        EditorialDto eDto1 = new EditorialDto(1L, "Minotauro", "Minotauro Ediciones SA", "30-61649666-9", "Barcelona", "Av. Diagonal 662", "34928000", emailDto1, bookDtoList1);
        EditorialDto eDto2 = new EditorialDto(2L, "Ivrea", "Editorial Ivrea S.L", "28-78762236-0", "Buenos Aires", "Presidente Perón 8561", "59971724", emailDto2, bookDtoList2);

        bookDtoList1.add(new BookDto(3L, "¿Sueñan los androides con ovejas eléctricas?", "Philip K. Dick", 1968, 50));
        bookDtoList2.add(new BookDto(1L, "Dragon Ball Tomo 2", "Akira Toriyama", 1995, 150));
        bookDtoList2.add(new BookDto(4L, "Neon Genesis Evangelion Tomo 5", "Yoshiyuki Sadamoto", 1994, 200));

        editorialDtoList.add(eDto1);
        editorialDtoList.add(eDto2);

        return editorialDtoList;
    }

    public static List<Editorial> loadEditorials() {

        Editorial editorial = new Editorial();
        List<Editorial> editorialList = new ArrayList<>();
        Set<Book> bookList1 = new HashSet<>();
        Set<Book> bookList2 = new HashSet<>();

        Email email1 = new Email(1L, "infominotauro@planeta.es", editorial);
        Email email2 = new Email(2L, "argentina@editorialivrea.com", editorial);

        Editorial e1 = new Editorial(1L, "Minotauro", "Minotauro Ediciones SA", "30-61649666-9", "Barcelona", "Av. Diagonal 662", "34928000", email1, bookList1);
        Editorial e2 = new Editorial(2L, "Ivrea", "Editorial Ivrea S.L", "28-78762236-0", "Buenos Aires", "Presidente Perón 8561", "59971724", email2, bookList2);

        email1.setEditorial(e1);
        email2.setEditorial(e2);

        bookList1.add(new Book(3L, "¿Sueñan los androides con ovejas eléctricas?", "Philip K. Dick", 1968, 50, e1));
        bookList2.add(new Book(1L, "Dragon Ball Tomo 2", "Akira Toriyama", 1995, 150, e2));
        bookList2.add(new Book(4L, "Neon Genesis Evangelion Tomo 5", "Yoshiyuki Sadamoto", 1994, 200, e2));

        editorialList.add(e1);
        editorialList.add(e2);

        return editorialList;
    }

    public static EditorialDto loadNewEditorialDto() {

        Set<BookDto> bookDtoList = new HashSet<>();

        EmailDto emailDto = new EmailDto(3L, "info@cajanegraeditora.com.ar");
        EditorialDto editorialDto = new EditorialDto (3L, "Caja Negra", "Editora Caja Negra SA", "34-50506969-7", "Buenos Aires", "Castillo 1486", "45877440", emailDto, bookDtoList);

        bookDtoList.add(new BookDto(5L, "K-Punk Volumen 1", "Mark Fisher", 2019, 20));
        bookDtoList.add(new BookDto (6L, "K-Punk Volumen 2", "Mark Fisher", 2020, 25));

        return editorialDto;
    }

    public static EditorialDto loadUpdateEditorialDto() {

        Set<BookDto> bookDtoList = new HashSet<>();

        EmailDto emailDto = new EmailDto(1L, "contacto@minotauro.es");
        EditorialDto editorialDto = new EditorialDto (1L, "Minotauro EDICIONES", "Minotauro Ediciones SA", "30-61649666-9", "Madrid", "Costa 662", "34928000", emailDto, bookDtoList);

        return editorialDto;
    }

    public static EditorialDto loadUpdateEditorialDtoExpected() {

        Set<BookDto> bookDtoList = new HashSet<>();

        EmailDto emailDto = new EmailDto(1L, "contacto@minotauro.es");
        EditorialDto editorialDto = new EditorialDto (1L, "Minotauro EDICIONES", "Minotauro Ediciones SA", "30-61649666-9", "Madrid", "Costa 662", "34928000", emailDto, bookDtoList);

        bookDtoList.add(new BookDto(3L, "¿Sueñan los androides con ovejas eléctricas?", "Philip K. Dick", 1968, 50));

        return editorialDto;
    }

    public static EditorialDto loadUpdateEditorialDtoExpected2() {

        Set<BookDto> bookDtoList = new HashSet<>();

        EmailDto emailDto = new EmailDto(2L, "argentina@editorialivrea.com");
        EditorialDto editorialDto = new EditorialDto(2L, "Ivrea", "Editorial Ivrea S.L", "28-78762236-0", "Buenos Aires", "Presidente Perón 8561", "59971724", emailDto, bookDtoList);

        bookDtoList.add(new BookDto(1L, "Dragon Ball Tomo 2", "Akira Toriyama", 1995, 150));
        bookDtoList.add(new BookDto(4L, "Neon Genesis Evangelion Tomo 5", "Yoshiyuki Sadamoto", 1994, 200));
        bookDtoList.add(new BookDto(2L, "Uzumaki", "Junji Ito", 1999, 140));

        return editorialDto;
    }


    // Para BookServiceTest
    public static List<BookDto> loadBooksDto() {

        List<BookDto> booksDto = new ArrayList<>();
        booksDto.add(new BookDto(1L, "Black Paradox", "Junji Ito", 2009, 150));
        booksDto.add(new BookDto(2L, "Uzumaki", "Junji Ito", 1999, 140));

        return booksDto;
    }

    // Para BookServiceTest
    public static List<Book> loadBooks() {

        Editorial e1 = new Editorial();
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Black Paradox", "Junji Ito", 2009, 150, e1));
        books.add(new Book(2L, "Uzumaki", "Junji Ito", 1999, 140, e1));

        return books;
    }





    public static List<BookDto> loadExpectedJson() {

        List<BookDto> books = new ArrayList<>();
        Set<BookDto> bookDtoList1 = new HashSet<>();
        Set<BookDto> bookDtoList2 = new HashSet<>();

        EmailDto emailDto1 = new EmailDto(1L, "infominotauro@planeta.es");
        EmailDto emailDto2 = new EmailDto(2L, "argentina@editorialivrea.com");

        EditorialDto eDto1 = new EditorialDto(1L, "Minotauro", "Minotauro Ediciones SA", "30-61649666-9", "Barcelona", "Av. Diagonal 662", "34928000", emailDto1, bookDtoList1);
        EditorialDto eDto2 = new EditorialDto(2L, "Ivrea", "Editorial Ivrea S.L", "28-78762236-0", "Buenos Aires", "Presidente Perón 8561", "59971724", emailDto2, bookDtoList2);

        BookDto bookDto1 = new BookDto(1L, "¿Sueñan los androides con ovejas eléctricas?", "Philip K. Dick", 1968, 50);
        BookDto bookDto4 = new BookDto(4L, "Neon Genesis Evangelion Tomo 5", "Yoshiyuki Sadamoto", 1994, 200);
        BookDto bookDto2 = new BookDto(2L, "Black Paradox", "Junji Ito", 2009, 150);
        BookDto bookDto3 = new BookDto(3L, "Uzumaki", "Junji Ito", 1999, 140);

        bookDtoList1.add(bookDto1);
        bookDtoList2.add(bookDto2);
        bookDtoList2.add(bookDto3);
        bookDtoList2.add(bookDto4);
        books.add(bookDto1);
        books.add(bookDto2);
        books.add(bookDto3);

        return books;

    }

    public static BookDto loadUpdateBook() {

        BookDto bookDto1 = new BookDto(1L, "¿Sueñan los androides con ovejas eléctricas? - Nueva Edición", "Philip K. Dick", 1968, 320);

        return bookDto1;

    }

    public static BookDto loadUpdateBookBadRequest() {

        BookDto bookDto1 = new BookDto(1L, "a", "a", 4968, -1);

        return bookDto1;

    }

}
