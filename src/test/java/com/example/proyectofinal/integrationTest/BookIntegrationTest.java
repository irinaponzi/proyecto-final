package com.example.proyectofinal.integrationTest;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.ErrorDto;
import com.example.proyectofinal.dto.RespBookDto;
import com.example.proyectofinal.dto.RespMessageDto;
import com.example.proyectofinal.repository.BookRepository;
import com.example.proyectofinal.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepository bookRepository;

    ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();


    @Test
    @Order(1)
    @DisplayName("Test getAllBooks OK")
    public void findAllBooksTestOK() throws Exception {

        List<BookDto> bookDtoList = Utils.loadExpectedJson();
        String expectedJson = writer.writeValueAsString(bookDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get ("/getAllBooks"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(2)
    @DisplayName("Test findBookById OK")
    public void findBookByIdTestOK() throws Exception {

        Long id = 1L;
        BookDto bookDto = Utils.loadExpectedJson().get(0);
        String expectedJson = writer.writeValueAsString(bookDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/findBookById/{id}", id))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(3)
    @DisplayName("Test findBookById NOT OK")
    public void findBookByIdTestNotOK() throws Exception {

        Long id = 6L;
        ErrorDto errorDto = new ErrorDto(404, "El libro solicitado no fue encontrado");
        String expectedJson = writer.writeValueAsString(errorDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/findBookById/{id}", id))
                .andDo(print())
                .andExpectAll(
                        status().is4xxClientError(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(4)
    @DisplayName("Test findBooksByName OK")
    public void findBooksByNameTestOK() throws Exception {

        List<BookDto> bookDtoList = Utils.loadExpectedJson();
        String expectedJson = writer.writeValueAsString(bookDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get ("/findBooksByName"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(5)
    @DisplayName("Test findBookByAuthor OK")
    public void findBookByAuthorTestOK() throws Exception {

        String author = "Junji Ito";
        List<BookDto> bookDtoList = new ArrayList<>();
        BookDto bookDto1 = Utils.loadExpectedJson().get(1);
        BookDto bookDto2 = Utils.loadExpectedJson().get(2);
        bookDtoList.add(bookDto1);
        bookDtoList.add(bookDto2);

        String expectedJson = writer.writeValueAsString(bookDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/findBookByAuthor/{author}", author))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(6)
    @DisplayName("Test updateBookById OK")
    public void updateBookByIdTestOK() throws Exception {

        Long id = 1L;
        BookDto payload = Utils.loadUpdateBookDto();
        RespBookDto respBookDto = new RespBookDto(payload, "El libro se actualizó exitosamente");

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(respBookDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/updateBookById/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(7)
    @DisplayName("Test updateBookById NOT OK Bad Request")
    public void updateBookByIdTestNotOK() throws Exception {

        Long id = 1L;
        BookDto payload = Utils.loadUpdateBookDtoBadRequest();

        List<ErrorDto> errorDtoList = new ArrayList<>();
        ErrorDto errorDto1 = new ErrorDto(400, "El stock debe ser igual o superior a cero");
        ErrorDto errorDto2 = new ErrorDto(400, "El título debe poseer entre 2 y 120 caracteres");
        errorDtoList.add(errorDto1);
        errorDtoList.add(errorDto2);

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(errorDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/updateBookById/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(8)
    @DisplayName("Test deleteBookById OK")
    public void deleteBookByIdTestOK() throws Exception {

        Long id = 3L;
        RespMessageDto respMessageDto = new RespMessageDto("El libro se eliminó exitosamente");
        String expectedJson = writer.writeValueAsString(respMessageDto);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/deleteBookById/{id}", id))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }
}
