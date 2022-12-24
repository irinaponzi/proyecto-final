package com.example.proyectofinal.serviceTest;

import com.example.proyectofinal.dto.*;
import com.example.proyectofinal.entity.Editorial;
import com.example.proyectofinal.exceptions.NotFoundException;
import com.example.proyectofinal.repository.EditorialRepository;
import com.example.proyectofinal.service.EditorialService;
import com.example.proyectofinal.utils.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditorialServiceTest {

    @Mock
    private EditorialRepository editorialRepository;

    @InjectMocks
    private EditorialService editorialService;

    @Test
    @Order(1)
    @DisplayName("Test obtener todas las editoriales OK")
    void findAllTestOK() {

        List<Editorial> EditorialListReturn = Utils.loadEditorials();
        List<EditorialDto> EditorialListExpected = Utils.loadEditorialsDto();

        when(editorialRepository.findAll()).thenReturn(EditorialListReturn);
        List<EditorialDto> EditorialListActual = editorialService.findAllEditorials();

        Assertions.assertAll(() -> {
            assertEquals(EditorialListExpected, EditorialListActual);
            assertEquals(EditorialListExpected.size(), EditorialListActual.size());
            assertEquals(EditorialListExpected.get(0), EditorialListActual.get(0));
        });
    }

    @Test
    @Order(2)
    @DisplayName("Test obtener editorial por nombre OK")
    void findEditorialByNameTestOK() {

        String name = "Minotauro";
        Editorial editorialReturn = Utils.loadEditorials().get(0);
        EditorialDto editorialExpected = Utils.loadEditorialsDto().get(0);

        when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(editorialReturn);
        EditorialDto editorialActual = editorialService.findEditorialByName(name);


        assertEquals(editorialExpected, editorialActual);

    }

    @Test
    @Order(3)
    @DisplayName("Test obtener editorial por nombre NOT OK")
    void findEditorialByNameNotOK() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            String name = "Minotauro";
            NotFoundException eExpected = new NotFoundException("La editorial solicitada no fue encontrada");

            when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(null);
            EditorialDto respActual = editorialService.findEditorialByName(name);

            assertEquals(eExpected, respActual);
        });
    }

    @Test
    @Order(4)
    @DisplayName("Test guardar editorial OK")
    void saveEditorialTestOK() {

        EditorialDto editorialDto = Utils.loadEditorialsDto().get(0);

        RespEditorialDto respExpected = new RespEditorialDto(editorialDto, "La editorial se guardó exitosamente");
        RespEditorialDto respActual = editorialService.saveEditorial(editorialDto);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(11)
    @DisplayName("Test obtener el stock total de libros de una editorial OK")
    void getStockByEditorialTestOK() {

        String name = "Minotauro";
        Editorial editorialReturn = Utils.loadEditorials().get(0);
        RespMessageDto respExpected  = new RespMessageDto("Se dispone de 290 libros en stock de la editorial Minotauro");

        when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(editorialReturn);
        RespMessageDto respActual = editorialService.getEditorialStockByEditorialName(name);

        assertEquals(respExpected, respActual);

    }


}
