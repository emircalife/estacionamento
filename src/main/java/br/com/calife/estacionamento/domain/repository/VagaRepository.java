package br.com.calife.estacionamento.domain.repository;

import br.com.calife.estacionamento.domain.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {

    Optional<Vaga> findById(Long id);

    Long countBySetorId(Long idSetor);

    Long countBySetorIdAndOcupadaTrue(Long idSetor);

}
