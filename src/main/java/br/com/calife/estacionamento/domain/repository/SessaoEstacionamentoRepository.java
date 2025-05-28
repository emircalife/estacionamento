package br.com.calife.estacionamento.domain.repository;

import br.com.calife.estacionamento.domain.entity.SessaoEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessaoEstacionamentoRepository extends JpaRepository<SessaoEstacionamento, Integer> {

    Optional<SessaoEstacionamento> findByVeiculoPlacaAndAtiva(String placa, Boolean ativa);

}