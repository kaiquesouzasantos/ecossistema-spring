package com.parkingcontrolapi.repository;

import com.parkingcontrolapi.model.VagaEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamento, UUID> {
    /* METODO PERSONALIZADO

    - construção:
            (exists | find | count) + By + <nomeCampo1> + [operador_logico_extenso] + <nomeCampo2>
    - exemplo:
            existsByPlacaCarro()

     - observação:
            operadores como distinct podem ser utilizados
     */

    boolean existsByPlacaCarro(String placaCarro);
    boolean existsByNumero(String numero);
    boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}
