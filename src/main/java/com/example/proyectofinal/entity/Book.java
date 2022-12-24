package com.example.proyectofinal.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long id;

    private String title;
    private String author;
    private Integer publicationDate;
    private Integer stock;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "editorial_id", nullable = false)
    private Editorial editorial;

}
