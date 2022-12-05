package com.parkingcontrolapi.controller;

import com.parkingcontrolapi.dto.VagaEstacionamentoDTO;
import com.parkingcontrolapi.model.VagaEstacionamento;
import com.parkingcontrolapi.service.VagaEstacionamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaEstacionamentoController {
    @Autowired private VagaEstacionamentoService vagaEstacionamentoService;

    @GetMapping("/list")
    public ResponseEntity<Page<VagaEstacionamento>> getAllVagaEstacionamento(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        // Pageable -> paginacao de elemento, contendo os elementos e informações de sumarização e contextualização
        // @PageableDefault(parametroKey = valor) -> podem ser passados pela URL, sendo uma sobreposição de valores default na paginação
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVagaEstacionamentoById(@PathVariable("id") UUID id){
        Optional<VagaEstacionamento> vaga = vagaEstacionamentoService.findById(id);

        if(vaga.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }

        return ResponseEntity.status(HttpStatus.OK).body(vaga.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addVagaEstacionamento(@RequestBody @Valid VagaEstacionamentoDTO vagaEstacionamentoDTO){
        if(vagaEstacionamentoService.existsByNumero(vagaEstacionamentoDTO.getNumero())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("VAGA OCUPADA");
        }

        if(vagaEstacionamentoService.existsByPlacaCarro(vagaEstacionamentoDTO.getPlacaCarro())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CARRO JA CADASTRADO");
        }

        if(vagaEstacionamentoService.existsByApartamentoAndBloco(vagaEstacionamentoDTO.getApartamento(), vagaEstacionamentoDTO.getBloco())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JA POSSUI VAGA DE ESTACIONAMENTO");
        }

        var vagaEstacionamento = new VagaEstacionamento();

        // BeanUtils.copyProperties(origem, destino) -> transferencia de informações(OBJ_ORIGEM -> JSON_ORIGEM (CAST) -> JSON_DESTINO -> OBJ_DESTINO)
        BeanUtils.copyProperties(vagaEstacionamentoDTO, vagaEstacionamento);
        vagaEstacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.save(vagaEstacionamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editVagaEstacionamento(@PathVariable("id") UUID id, @RequestBody @Valid VagaEstacionamentoDTO vagaEstacionamentoDTO){
        Optional<VagaEstacionamento> vagaEstacionamentoExistente = vagaEstacionamentoService.findById(id);

        if(vagaEstacionamentoExistente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }

        var vagaEstacionamento = new VagaEstacionamento();

        BeanUtils.copyProperties(vagaEstacionamentoDTO, vagaEstacionamento);
        vagaEstacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        vagaEstacionamento.setId(vagaEstacionamentoExistente.get().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.save(vagaEstacionamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVagaEstacionamento(@PathVariable("id") UUID id){
        Optional<VagaEstacionamento> vaga = vagaEstacionamentoService.findById(id);

        if(vaga.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }

        vagaEstacionamentoService.delete(vaga.get());
        return ResponseEntity.status(HttpStatus.OK).body("REMOVIDO COM SUCESSO");
    }
}
