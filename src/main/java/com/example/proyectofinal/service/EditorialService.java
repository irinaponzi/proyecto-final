package com.example.proyectofinal.service;

import com.example.proyectofinal.dto.*;
import com.example.proyectofinal.entity.Book;
import com.example.proyectofinal.entity.Editorial;
import com.example.proyectofinal.entity.Email;
import com.example.proyectofinal.exceptions.NotFoundException;
import com.example.proyectofinal.repository.EditorialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EditorialService implements IEditorialService {

    EditorialRepository editorialRepository;

    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    ModelMapper mapper = new ModelMapper();


    @Override
    public List<EditorialDto> findAllEditorials() {

        List<Editorial> editorialList = editorialRepository.findAll();
        List<EditorialDto> editorialDtoList = new ArrayList<>();
        editorialList.stream().forEach(e -> editorialDtoList.add(mapper.map(e, EditorialDto.class)));

        return editorialDtoList;
    }

    @Override
    public EditorialDto findEditorialByName(String name) {

        Optional<Editorial> editorial = Optional.ofNullable(editorialRepository.findEditorialByNameContainingIgnoreCase(name));
        if(editorial.isPresent()) {
            Editorial e = editorial.get();
            EditorialDto response = mapper.map(e, EditorialDto.class);

            return response;
        }

        throw new NotFoundException("La editorial solicitada no fue encontrada");
    }

    @Override
    public RespEditorialDto saveEditorial(EditorialDto editorialDto) {

        EmailDto emailDto = editorialDto.getEmail();
        mapper.map(emailDto, Email.class);

        Editorial editorial = mapper.map(editorialDto, Editorial.class);
        editorial.getBooks().forEach(b -> b.setEditorial(editorial));

        editorialRepository.save(editorial);

        RespEditorialDto response = new RespEditorialDto();
        response.setEditorial(mapper.map(editorial, EditorialDto.class));
        response.setResponse("La editorial se guardó exitosamente");

        return response;
    }

    @Override
    public RespEditorialDto updateDataEditorialById(Long id, EditorialDto eDto) {

        Optional<Editorial> editorial = editorialRepository.findById(id);
        if(editorial.isPresent()) {
            Editorial eExist = editorial.get();

            eExist.setName(eDto.getName());
            eExist.setBusinessName(eDto.getBusinessName());
            eExist.setCuit(eDto.getCuit());
            eExist.setCity(eDto.getCity());
            eExist.setAddress(eDto.getAddress());
            eExist.setTelephone(eDto.getTelephone());

            Email emailExist = eExist.getEmail();
            emailExist.setDescription(eDto.getEmail().getDescription());
            eExist.setEmail(emailExist);

            editorialRepository.save(eExist);

            RespEditorialDto response = new RespEditorialDto();
            response.setEditorial(mapper.map(eExist, EditorialDto.class));
            response.setResponse("La editorial se actualizó exitosamente");

            return response;
        }

        throw new NotFoundException("No se puede actualizar. La editorial seleccionada no existe");
    }

    @Override
    public RespEditorialDto addEditorialBookByEditorialId(Long id, BookDto bookDto) {

        Optional<Editorial> editorial = editorialRepository.findById(id);
        if(editorial.isPresent()) {
            Editorial eExist = editorial.get();

            Book book = mapper.map(bookDto, Book.class);
            book.setEditorial(eExist);

            eExist.getBooks().add(book);
            editorialRepository.save(eExist);

            RespEditorialDto response = new RespEditorialDto();
            response.setEditorial(mapper.map(eExist, EditorialDto.class));
            response.setResponse("El libro se añadió exitosamente");

            return response;
        }

        throw new NotFoundException("El libro no se puede añadir. La editorial seleccionada no existe");
    }

    @Override
    public RespMessageDto deleteEditorialById(Long id) {

        Optional<Editorial> editorial = editorialRepository.findById(id);
        if(editorial.isPresent()) {
            editorialRepository.deleteById(id);

            RespMessageDto response = new RespMessageDto();
            response.setResponse("La editorial se eliminó exitosamente");

            return response;
        }

        throw new NotFoundException("No se puede eliminar. La editorial seleccionada no existe");
    }

    @Override
    public RespMessageDto getEditorialStockByEditorialName(String name) {

        Optional<Editorial> editorial = Optional.ofNullable(editorialRepository.findEditorialByNameContainingIgnoreCase(name));
        if(editorial.isPresent()) {
            Editorial e = editorial.get();
            EditorialDto editorialDto = mapper.map(e, EditorialDto.class);

            String editorialName = editorialDto.getName();
            int stockSum = stockSum(editorialDto);

            RespMessageDto response = new RespMessageDto();
            response.setResponse("Se dispone de " + stockSum + " libros en stock de la editorial " + editorialName);

            return response;
        }

        throw new NotFoundException("La editorial seleccionada no fue encontrada");
    }

    private static int stockSum(EditorialDto editorial) {

        List<Integer> editorialStock = editorial.getBooks().stream()
                .map(b -> b.getStock()).collect(Collectors.toList());

        int stockSum = editorialStock.stream().
                mapToInt(s -> s).sum();

        return stockSum;
    }
}


