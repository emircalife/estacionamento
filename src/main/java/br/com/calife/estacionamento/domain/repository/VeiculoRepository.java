package br.com.calife.estacionamento.domain.repository;

import br.com.calife.estacionamento.domain.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByPlaca(String placa);

}
