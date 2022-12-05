package com.parkingcontrolapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_VAGA_ESTACIONAMENTO")
public class VagaEstacionamento implements Serializable {
    @Serial // -> anotacao de implementacao de seguranca basica recomendada
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // UUID -> (identificador único universal imutável) com 128 bits
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String numero;

    @Column(nullable = false, unique = true, length = 7)
    private String placaCarro;

    @Column(nullable = false, length = 70)
    private String marcaCarro;

    @Column(nullable = false, length = 70)
    private String modeloCarro;

    @Column(nullable = false, length = 70)
    private String corCarro;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @Column(nullable = false, length = 130)
    private String nomeResponsavel;

    @Column(nullable = false, length = 30)
    private String apartamento;

    @Column(nullable = false, length = 30)
    private String bloco;
}
