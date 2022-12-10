package com.parkingcontrolapi.service;

import com.parkingcontrolapi.model.VagaEstacionamento;
import com.parkingcontrolapi.repository.VagaEstacionamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagaEstacionamentoService {
    @Autowired private VagaEstacionamentoRepository vagaEstacionamentoRepository;

    @Transactional
    // -> garante o roll-back, ou seja, em caso de erro mantem a integridade, resetando a operação, utilizado em operações DML
    public VagaEstacionamento save(VagaEstacionamento vagaEstacionamento){
        return vagaEstacionamentoRepository.save(vagaEstacionamento);
    }

    @Transactional
    public void delete(VagaEstacionamento vaga){
        vagaEstacionamentoRepository.delete(vaga);
    }

    public boolean existsByPlacaCarro(String placa){
        return vagaEstacionamentoRepository.existsByPlacaCarro(placa);
    }

    public boolean existsByNumero(String numero){
        return vagaEstacionamentoRepository.existsByNumero(numero);
    }

    public boolean existsByApartamentoAndBloco(String apartamento, String bloco){
        return vagaEstacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
    }

    public Optional<VagaEstacionamento> findById(UUID id){
        return vagaEstacionamentoRepository.findById(id);
    }

    public Page<VagaEstacionamento> findAll(Pageable pageable){
        return vagaEstacionamentoRepository.findAll(pageable);
    }
}
