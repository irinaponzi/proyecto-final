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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void findAllEditorialsTestOK() {

        List<Editorial> editorialListReturn = Utils.loadEditorials();
        List<EditorialDto> editorialListExpected = Utils.loadEditorialsDto();

        when(editorialRepository.findAll()).thenReturn(editorialListReturn);
        List<EditorialDto> editorialListActual = editorialService.findAllEditorials();

        Assertions.assertAll(() -> {
            assertEquals(editorialListExpected, editorialListActual);
            assertEquals(editorialListExpected.size(), editorialListActual.size());
            assertEquals(editorialListExpected.get(0), editorialListActual.get(0));
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
    void findEditorialByNameTestNotOK() {

        String name = "Minotauro";
        NotFoundException exExpected = new NotFoundException("La editorial solicitada no fue encontrada");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(null);
            editorialService.findEditorialByName(name);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("Test guardar editorial OK")
    void saveEditorialTestOK() {

        EditorialDto editorialDto = Utils.loadNewEditorialDto();

        RespEditorialDto editorialExpected = new RespEditorialDto(editorialDto, "La editorial se guardó exitosamente");
        RespEditorialDto editorialActual = editorialService.saveEditorial(editorialDto);

        assertEquals(editorialExpected, editorialActual);
    }

    @Test
    @Order(5)
    @DisplayName("Test actualizar editorial por Id OK")
    void updateEditorialByIdTestOK() {

        Long id = 1L;
        EditorialDto editorialDto = Utils.loadUpdateEditorialDto();

        Editorial editorialReturn = Utils.loadEditorials().get(0);
        RespEditorialDto respExpected = new RespEditorialDto(editorialDto, "La editorial se actualizó exitosamente");

        when(editorialRepository.findById(id)).thenReturn(Optional.ofNullable(editorialReturn));
        RespEditorialDto respActual = editorialService.updateEditorialById(id, editorialDto);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(6)
    @DisplayName("Test actualizar editorial por Id NOT OK")
    void updateEditorialByIdTestNotOK() {

        Long id = 1L;
        EditorialDto editorialDto = Utils.loadUpdateEditorialDto();

        Optional<Editorial> editorialReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("No se puede actualizar. La editorial seleccionada no existe");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(editorialRepository.findById(id)).thenReturn(editorialReturn);
            editorialService.updateEditorialById(id, editorialDto);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(7)
    @DisplayName("Test añadir libro a editorial por Id OK")
    void addBookByEditorialIdTestOK() {

        Long id = 2L;
        BookDto bookDto = Utils.loadBooksDto().get(1);
        EditorialDto editorialDtoExpected = Utils.loadUpdateEditorialDtoExpected();

        Editorial editorialReturn = Utils.loadEditorials().get(1);
        RespEditorialDto respExpected = new RespEditorialDto(editorialDtoExpected, "El libro se añadió exitosamente");

        when(editorialRepository.findById(id)).thenReturn(Optional.ofNullable(editorialReturn));
        RespEditorialDto respActual = editorialService.addBookByEditorialId(id, bookDto);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(8)
    @DisplayName("Test añadir libro a editorial por Id NOT OK")
    void addBookByEditorialIdTestNotOK() {

        Long id = 2L;
        BookDto bookDto = Utils.loadBooksDto().get(1);

        Optional<Editorial> editorialReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("El libro no se puede añadir. La editorial seleccionada no existe");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(editorialRepository.findById(id)).thenReturn(editorialReturn);
            editorialService.addBookByEditorialId(id, bookDto);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("Test eliminar editorial por Id OK")
    void deleteEditorialByIdTestOK() {

        Long id = 1L;
        Editorial editorialReturn = Utils.loadEditorials().get(0);
        RespMessageDto respExpected = new RespMessageDto("La editorial se eliminó exitosamente");

        when(editorialRepository.findById(id)).thenReturn(Optional.of(editorialReturn));
        RespMessageDto respActual = editorialService.deleteEditorialById(id);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(10)
    @DisplayName("Test eliminar editorial por Id NOT OK")
    void deleteEditorialByIdTestNotOK() {

        Long id = 1L;
        Optional<Editorial> editorialReturn = Optional.empty();
        NotFoundException exExpected = new NotFoundException("No se puede eliminar. La editorial seleccionada no existe");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(editorialRepository.findById(id)).thenReturn(editorialReturn);
            editorialService.deleteEditorialById(id);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }

    @Test
    @Order(11)
    @DisplayName("Test obtener el stock total de libros de una editorial por nombre OK")
    void getEditorialStockByEditorialNameTestOK() {

        String name = "Ivrea";
        Editorial editorialReturn = Utils.loadEditorials().get(1);
        RespMessageDto respExpected  = new RespMessageDto("Se dispone de 350 libros en stock de la editorial Ivrea");

        when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(editorialReturn);
        RespMessageDto respActual = editorialService.getEditorialStockByEditorialName(name);

        assertEquals(respExpected, respActual);
    }

    @Test
    @Order(12)
    @DisplayName("Test obtener el stock total de libros de una editorial por nombre NOT OK")
    void getEditorialStockByEditorialNameNotOK() {

        String name = "Ivrea";
        NotFoundException exExpected = new NotFoundException("La editorial seleccionada no fue encontrada");

        NotFoundException exActual = assertThrows(NotFoundException.class, () -> {
            when(editorialRepository.findEditorialByNameContainingIgnoreCase(name)).thenReturn(null);
            editorialService.getEditorialStockByEditorialName(name);
        });

        assertEquals(exExpected.getMessage(), exActual.getMessage());
    }
}
