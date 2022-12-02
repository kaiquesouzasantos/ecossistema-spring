package com.example.thymeleafspring.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autor")
@Entity(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    public Autor(String nome, Long id){
        this.nome = nome;
        this.id = id;
    }
}
