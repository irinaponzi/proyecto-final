package com.example.proyectofinal.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorialDto {

    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 60, message = "El nombre debe poseer entre 2 y 60 caracteres")
    private String name;
    @Size(min = 4, max = 60, message = "La Razón Social debe poseer entre 4 y 60 caracteres")
    private String businessName;
    @NotBlank(message = "El CUIT no puede estar vacío")
    @Pattern(regexp = "[0-9]{2}+\\-+[0-9]{8}+\\-+[0-9]", message = "El CUIT debe poseer 13 caracteres, incluyendo guiones")
    private String cuit;
    @Size(min = 4, max = 30, message = "La ciudad debe poseer entre 4 y 30 caracteres")
    private String city;
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 4, max = 60, message = "La dirección debe poseer entre 4 y 60 caracteres")
    private String address;
    @Pattern(regexp = "[0-9]{6,16}", message = "El número de teléfono no puede contener puntos, guiones ni espacios y debe poseer entre 6 y 16 dígitos")
    private String telephone;
    @Valid
    @NotNull(message = "El email no puede estar vacío")
    private EmailDto email;
    @Valid
    private Set<BookDto> books;

}
