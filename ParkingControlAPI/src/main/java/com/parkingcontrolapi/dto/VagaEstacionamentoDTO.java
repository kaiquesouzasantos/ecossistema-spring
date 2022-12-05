package com.parkingcontrolapi.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class VagaEstacionamentoDTO {
    // DTO -> Data Transfer Object

    @NotBlank
    private String numero;

    @NotBlank
    @Size(max = 7)
    private String placaCarro;

    @NotBlank
    private String modeloCarro;

    @NotBlank
    private String marcaCarro;

    @NotBlank
    private String corCarro;

    @NotBlank
    private String nomeResponsavel;

    @NotBlank
    private String apartamento;

    @NotBlank
    private String bloco;
}
