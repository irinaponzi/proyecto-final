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
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
    public void testGetAllBooksOK() throws Exception {

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
    public void testFindBookByIdOK() throws Exception {

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
    public void testFindBookByIdNotOK() throws Exception {

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
    public void testFindBooksByNameOK() throws Exception {

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
    public void findBookByAuthorOK() throws Exception {

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
    public void testUpdateBookByIdOK() throws Exception {

        Long id = 1L;
        BookDto payload = Utils.loadUpdateBook();
        RespBookDto respBookDto = new RespBookDto(Utils.loadUpdateBook(), "El libro se actualizó exitosamente");

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(respBookDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/updateBook/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    /*@Test
    @Order(7)
    @DisplayName("Test updateBookById NOT OK Bad Request")
    public void testUpdateBookByIdNotOK() throws Exception {

        Long id = 1L;
        String status =
        BookDto payload = Utils.loadUpdateBookBadRequest();
        RespBookDto respBookDto = new RespBookDto(Utils.loadUpdateBook(), "El libro se actualizó exitosamente");

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(respBookDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/updateBook/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }*/

    @Test
    @Order(8)
    @DisplayName("Test deleteBookById OK")
    public void testDeleteBookByIdOK() throws Exception {

        Long id = 3L;
        RespMessageDto respMessageDto = new RespMessageDto("El libro se eliminó exitosamente");
        String expectedJson = writer.writeValueAsString(respMessageDto);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/deleteBook/{id}", id))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }




      /*@Test
         public void testPostRespuestaCompletaOK() throws Exception {

        HolaDtoParaTest payload = new HolaDtoParaTest("Adios Jose");
        HolaDtoParaTest responseDto = new HolaDtoParaTest("Adios Jose tkm nv en Narnia");

        String payloadJson = writer.writeValueAsString(payload);
        String responseJson = writer.writeValueAsString(responseDto);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/CrearSaludo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andReturn();

        assertEquals(responseJson, response.getResponse().getContentAsString());
        assertEquals(200, response.getResponse().getStatus());
        assertEquals("application/json", response.getResponse().getContentType());
    }
    */


}
