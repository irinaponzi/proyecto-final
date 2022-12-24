package com.example.proyectofinal.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBookDto {

    private BookDto book;
    private String response;


}
