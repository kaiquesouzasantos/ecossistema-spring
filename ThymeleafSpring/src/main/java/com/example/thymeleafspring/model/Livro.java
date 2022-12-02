package com.example.thymeleafspring.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")
@Entity(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(String nome, Autor autor){
        this.nome = nome;
        this.autor = autor;
    }
}
