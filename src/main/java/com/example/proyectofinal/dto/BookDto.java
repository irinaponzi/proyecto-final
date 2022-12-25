package com.example.proyectofinal.dto;

import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 2, max = 120, message = "El título debe poseer entre 2 y 120 caracteres")
    private String title;
    @NotBlank(message = "El autor no puede estar vacío")
    @Size(min = 4, max = 40, message = "El autor debe poseer entre 4 y 40 caracteres")
    private String author;
    @Min(value = 0001, message = "La fecha de publicación es incorrecta")
    @Max(value = 2999, message = "La fecha de publicación es incorrecta")
    private Integer publicationDate;
    @NotNull(message = "El stock debe ser igual o superior a cero")
    @PositiveOrZero(message = "El stock debe ser igual o superior a cero")
    private Integer stock;
    
}
