package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.RespMessageDto;
import com.example.proyectofinal.dto.RespEditorialDto;
import com.example.proyectofinal.dto.EditorialDto;
import com.example.proyectofinal.service.IEditorialService;
import com.example.proyectofinal.service.EditorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EditorialController {

    IEditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping("/getAllEditorials")
    public ResponseEntity<List<EditorialDto>> findAllEditorials() {
        return new ResponseEntity<>(editorialService.findAllEditorials(), HttpStatus.OK);
    }

    @GetMapping("/findEditorialByName/{name}")
    public ResponseEntity<EditorialDto> findEditorialByName(@PathVariable String name) {
        return new ResponseEntity<>(editorialService.findEditorialByName(name), HttpStatus.OK);
    }

    @PostMapping("/saveEditorial")
    public ResponseEntity<RespEditorialDto> saveEditorial(@Valid @RequestBody EditorialDto editorial) {
        return new ResponseEntity<>(editorialService.saveEditorial(editorial), HttpStatus.OK);
    }

    @PutMapping("/updateEditorialById/{id}")
    public ResponseEntity<RespEditorialDto> updateEditorialById(@PathVariable Long id, @Valid @RequestBody EditorialDto editorialDto) {
        return new ResponseEntity<>(editorialService.updateEditorialById(id, editorialDto), HttpStatus.OK);
    }

    @PutMapping("/addBookByEditorialId/{id}")
    public ResponseEntity<RespEditorialDto> addBookByEditorialId(@PathVariable Long id, @Valid @RequestBody BookDto bookDto)  {
        return new ResponseEntity<>(editorialService.addBookByEditorialId(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEditorialById/{id}")
    public ResponseEntity<RespMessageDto> deleteEditorialById(@PathVariable Long id) {
        return new ResponseEntity<>(editorialService.deleteEditorialById(id), HttpStatus.OK);
    }

    @GetMapping("/getEditorialStockByEditorialName/{name}")
    public ResponseEntity<RespMessageDto> getEditorialStockByEditorialName(@PathVariable String name) {
        return new ResponseEntity<>(editorialService.getEditorialStockByEditorialName(name), HttpStatus.OK);
    }

}
