package com.example.proyectofinal.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private Long id;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El email ingresado es incorrecto")
    private String description;


}
