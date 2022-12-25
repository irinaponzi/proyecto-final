package com.example.proyectofinal.service;

import com.example.proyectofinal.dto.BookDto;
import com.example.proyectofinal.dto.RespMessageDto;
import com.example.proyectofinal.dto.RespEditorialDto;
import com.example.proyectofinal.dto.EditorialDto;

import java.util.List;

public interface IEditorialService {

    List<EditorialDto> findAllEditorials();
    EditorialDto findEditorialByName(String name);
    RespEditorialDto saveEditorial(EditorialDto editorialDto);
    RespEditorialDto updateDataEditorialById(Long id, EditorialDto editorialDto);
    RespEditorialDto addEditorialBookByEditorialId(Long id, BookDto bookDto);
    RespMessageDto deleteEditorialById(Long id);
    RespMessageDto getEditorialStockByEditorialName(String name);

}
