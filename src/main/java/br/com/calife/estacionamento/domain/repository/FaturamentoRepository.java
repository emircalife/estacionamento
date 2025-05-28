package br.com.calife.estacionamento.domain.repository;

import br.com.calife.estacionamento.domain.entity.Faturamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturamentoRepository extends JpaRepository<Faturamento, Integer> {

//    @Query(value = """
//        select  d.`id`,
//                d.`altura`,
//                d.`comprimento`,
//                d.`largura`,
//                d.`descricao`
//        from `dimensoes` d
//        where d.`descricao` = :descricao
//    """, nativeQuery = true)
//    Optional<Faturamento> findByDescricao(String descricao);
}