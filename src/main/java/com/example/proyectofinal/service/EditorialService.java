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


    //Retorna una lista con todas las editoriales
    @Override
    public List<EditorialDto> findAllEditorials() {

        List<Editorial> editorialList = editorialRepository.findAll();
        List<EditorialDto> editorialDtoList = new ArrayList<>();
        editorialList.stream().forEach(e -> editorialDtoList.add(mapper.map(e, EditorialDto.class)));

        return editorialDtoList;
    }

    //Busca una editorial por su nombre
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

    //Guarda una editorial, y los libros pertenecientes a ella
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

    //Actualiza los datos de una editorial, indicada por su Id (no los libros)
    //Recibe un editorialDto con todos los atributos menos el Set<BookDto>
    @Override
    public RespEditorialDto updateEditorialDataById(Long id, EditorialDto editorialDto) {

        Optional<Editorial> editorial = editorialRepository.findById(id);
        if(editorial.isPresent()) {
            Editorial eExist = editorial.get();
            Editorial editorialUpdated = (mapper.map(editorialDto, Editorial.class));

            Email emailExist = eExist.getEmail();
            emailExist.setDescription(editorialUpdated.getEmail().getDescription());
            editorialUpdated.setEmail(emailExist);

            editorialUpdated.setId(eExist.getId());
            editorialUpdated.setBooks(eExist.getBooks());

            editorialRepository.save(editorialUpdated);

            RespEditorialDto response = new RespEditorialDto();
            response.setEditorial(mapper.map(editorialUpdated, EditorialDto.class));
            response.setResponse("La editorial se actualizó exitosamente");

            return response;
        }

        throw new NotFoundException("No se puede actualizar. La editorial seleccionada no existe");
    }

    //Agrega un nuevo libro a la editorial indicada por Id
    @Override
    public RespEditorialDto addBookByEditorialId(Long id, BookDto bookDto) {

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

    //Elimina la editorial indicada por Id y todos los libros pertenecientes a ella
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

    //Retorna el stock total de libros que posee una editorial
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


