package com.example.proyectofinal.serviceTest;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.RespBookDto;
import com.example.proyectofinal.dto.RespMessageDto;
import com.example.proyectofinal.entity.Book;
import com.example.proyectofinal.exceptions.NotFoundException;
import com.example.proyectofinal.repository.BookRepository;
import com.example.proyectofinal.service.BookService;
import com.example.proyectofinal.utils.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @Order(1)
    @DisplayName("Test obtener todos los libros OK")
    void findAllBooksTestOK() {

        List<Book> bookListReturn = Utils.loadBooks();
        List<BookDto> bookListExpected = Utils.loadBooksDto();

        when(bookRepository.findAll()).thenReturn(bookListReturn);
        List<BookDto> bookListActual = bookService.findAllBooks();

        Assertions.assertAll(() -> {
            assertEquals(bookListExpected, bookListActual);
            assertEquals(bookListExpected.size(), bookListActual.size());
            assertEquals(bookListExpected.get(0), bookListActual.get(0));
        });
    }

    @Test
    @Order(2)
    @DisplayName("Test obtener libro por Id OK")
    void findBookByIdTestOK() {

        Long id = 1L;
        Book bookReturn = Utils.loadBooks().get(0);
        BookDto bookExpected = Utils.loadBooksDto().get(0);

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookReturn));
        BookDto bookActual = bookService.findBookById(id);

        assertEquals(bookExpected, bookActual);
    }

    @Test
    @Order(3)
    @DisplayName("Test obtener libro por Id NOT OK")
    void findBookByIdTestNotOK() {

        Long id = 1L;
        Optional<Book> bookReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("El libro solicitado no fue encontrado");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(bookRepository.findById(id)).thenReturn(bookReturn);
            bookService.findBookById(id);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("Test obtener libro por título orden ascendente OK")
    void findBookByOrderByTitleAscTestOK() {

        List<Book> bookListReturn = Utils.loadBooks();
        List<BookDto> bookListExpected = Utils.loadBooksDto();

        when(bookRepository.findBookByOrderByTitleAsc()).thenReturn(bookListReturn);
        List<BookDto> bookListActual = bookService.findBookByOrderByTitleAsc();

        Assertions.assertAll(() -> {
            assertEquals(bookListExpected.get(0), bookListActual.get(0));
            assertEquals(bookListExpected.get(1), bookListActual.get(1));
        });
    }

    @Test
    @Order(5)
    @DisplayName("Test obtener libro por autor OK")
    void findBookByAuthorTestOK() {

        String author = "Junji Ito";
        List<Book> bookListReturn = Utils.loadBooks();
        List<BookDto> bookListExpected = Utils.loadBooksDto();

        when(bookRepository.findBookByAuthorContainingIgnoreCase(author)).thenReturn(bookListReturn);
        List<BookDto> bookListActual = bookService.findBookByAuthor(author);

        Assertions.assertAll(() -> {
            assertEquals(bookListExpected, bookListActual);
            assertEquals(bookListExpected.size(), bookListActual.size());
        });
    }

    @Test
    @Order(6)
    @DisplayName("Test obtener libro por autor NOT OK")
    void findBookByAuthorTestNotOK() {

        String author = "Junji Ito";
        List<Book> bookReturn = new ArrayList<>();
        NotFoundException exExpected = new NotFoundException("No fue encontrado ningún libro que coincida con la búsqueda");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(bookRepository.findBookByAuthorContainingIgnoreCase(author)).thenReturn(bookReturn);
            bookService.findBookByAuthor(author);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(7)
    @DisplayName("Test actualizar libro por Id OK")
    void updateBookByIdTestOK() {

        Long id = 2L;
        BookDto bookDto = new BookDto(2L,"Uzumaki - Nueva Edición", "Junji Ito",  2022, 150);

        Book bookReturn = Utils.loadBooks().get(1);
        RespBookDto respExpected = new RespBookDto(bookDto, "El libro se actualizó exitosamente");

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookReturn));
        RespBookDto respActual = bookService.updateBookById(id, bookDto);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(8)
    @DisplayName("Test actualizar libro por Id NOT OK")
    void updateBookByIdTestNotOK() {

        Long id = 2L;
        BookDto bookDto = new BookDto(2L,"Uzumaki - Nueva Edición", "Junji Ito",  2022, 150);

        Optional<Book> bookReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("El libro solicitado no fue encontrado");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(bookRepository.findById(id)).thenReturn(bookReturn);
            bookService.updateBookById(id, bookDto);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("Test eliminar libro por Id OK")
    void deleteBookByIdTestOK() {

        Long id = 2L;
        Book bookReturn = Utils.loadBooks().get(1);
        RespMessageDto respExpected = new RespMessageDto("El libro se eliminó exitosamente");

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookReturn));
        RespMessageDto respActual = bookService.deleteBookById(id);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(10)
    @DisplayName("Test eliminar libro por Id NOT OK")
    void deleteBookByIdTestNotOK() {

        Long id = 2L;
        Optional<Book> bookReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("No se puede eliminar. El libro seleccionado no existe");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(bookRepository.findById(id)).thenReturn(bookReturn);
            bookService.deleteBookById(id);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }
}



