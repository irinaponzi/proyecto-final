package com.example.proyectofinal.integrationTest;

import com.example.proyectofinal.dto.*;
import com.example.proyectofinal.repository.EditorialRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EditorialIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EditorialRepository editorialRepository;

    ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();


    @Test
    @Order(1)
    @DisplayName("Test getAllEditorials OK")
    public void findAllEditorialsTestOK() throws Exception {

        List<EditorialDto> editorialDtoList = Utils.loadExpectedJsonEditorial();
        String expectedJson = writer.writeValueAsString(editorialDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get ("/getAllEditorials"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(2)
    @DisplayName("Test findEditorialByName OK")
    public void findEditorialByNameTestOK() throws Exception {

        String name = "Minotauro";
        EditorialDto editorialDto = Utils.loadExpectedJsonEditorial().get(0);
        String expectedJson = writer.writeValueAsString(editorialDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/findEditorialByName/{name}", name))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(3)
    @DisplayName("Test saveEditorial OK")
    public void saveEditorialTestOK() throws Exception {

       EditorialDto payload = Utils.loadNewEditorialDto();
       RespEditorialDto respEditorialDto = new RespEditorialDto(payload, "La editorial se guardó exitosamente");

       String payloadJson = writer.writeValueAsString(payload);
       String expectedJson = writer.writeValueAsString(respEditorialDto);

       this.mockMvc.perform(MockMvcRequestBuilders.post("/saveEditorial")
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
    @Order(4)
    @DisplayName("Test saveEditorial NOT OK")
    public void saveEditorialTestNotOK() throws Exception {

        EditorialDto payload = Utils.loadSaveEditorialDtoBadRequest();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        ErrorDto errorDto1 = new ErrorDto(400, "El email ingresado es incorrecto");
        ErrorDto errorDto2 = new ErrorDto(400, "El CUIT debe poseer 13 caracteres, incluyendo guiones");
        errorDtoList.add(errorDto1);
        errorDtoList.add(errorDto2);

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(errorDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/saveEditorial")
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
    @Order(5)
    @DisplayName("Test updateEditorialById OK")
    public void updateEditorialByIdTestOK() throws Exception {

        Long id = 1L;
        EditorialDto payload = Utils.loadUpdateEditorialDto();
        EditorialDto editorialDtoExpected = Utils.loadUpdateEditorialDto();

        RespEditorialDto respEditorialDto = new RespEditorialDto(editorialDtoExpected, "La editorial se actualizó exitosamente");

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(respEditorialDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/updateEditorialById/{id}", id)
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
    @Order(6)
    @DisplayName("Test addBookByEditorialId OK")
    public void addBookByEditorialIdTestOK() throws Exception {

        Long id = 2L;
        BookDto payload = Utils.addBookDto();
        EditorialDto editorialDtoExpected = Utils.loadUpdateEditorialDtoExpected2();

        RespEditorialDto respEditorialDto = new RespEditorialDto(editorialDtoExpected, "El libro se añadió exitosamente");

        String payloadJson = writer.writeValueAsString(payload);
        String expectedJson = writer.writeValueAsString(respEditorialDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/addBookByEditorialId/{id}", id)
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
    @DisplayName("Test deleteEditorialById OK")
    public void deleteEditorialByIdTestOK() throws Exception {

        Long id = 1L;
        RespMessageDto respMessageDto = new RespMessageDto("La editorial se eliminó exitosamente");
        String expectedJson = writer.writeValueAsString(respMessageDto);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEditorialById/{id}", id))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }

    @Test
    @Order(8)
    @DisplayName("Test obtener el stock total de libros de una editorial por nombre OK")
    public void getEditorialStockByEditorialNameTestOK() throws Exception {

        String name = "Ivrea";
        RespMessageDto respMessageDto = new RespMessageDto("Se dispone de 290 libros en stock de la editorial Ivrea");
        String expectedJson = writer.writeValueAsString(respMessageDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/getEditorialStockByEditorialName/{name}", name))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().json(expectedJson)
                );
    }
}
