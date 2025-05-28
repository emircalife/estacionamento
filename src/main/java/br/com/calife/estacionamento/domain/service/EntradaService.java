package br.com.calife.estacionamento.domain.service;

import br.com.calife.estacionamento.core.exception.GenericException;
import br.com.calife.estacionamento.domain.entity.DTO.EntradaRequestDTO;
import br.com.calife.estacionamento.domain.entity.SessaoEstacionamento;
import br.com.calife.estacionamento.domain.entity.Setor;
import br.com.calife.estacionamento.domain.entity.Vaga;
import br.com.calife.estacionamento.domain.entity.Veiculo;
import br.com.calife.estacionamento.domain.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class EntradaService {
    @Autowired
    private final VeiculoRepository veiculoRepository;

    @Autowired
    private final VagaRepository vagaRepository;

    @Autowired
    private final SessaoEstacionamentoRepository sessaoRepository;

    @Autowired
    private final SetorRepository setorRepository;

    public void registrarEntrada(EntradaRequestDTO entradaRequestDTO) {
        Setor setor = setorRepository.findById(entradaRequestDTO.getIdSetor()).orElseThrow(() ->
                new GenericException("Setor nao encontrado"));

        Long totalVagas = vagaRepository.countBySetorId(setor.getId());
        Long vagasOcupadas = vagaRepository.countBySetorIdAndOcupadaTrue(setor.getId());
        double lotacao = vagasOcupadas.doubleValue() / totalVagas.doubleValue();

        log.info("Lotação atual do setor '${setor.nome}': ${(lotacao * 100).toInt()}%");

        if (lotacao >= 1.0) {
            log.warn("Setor está lotado. Entrada bloqueada.");
            throw new GenericException("Setor está lotado. Não é possível registrar nova entrada.");
        }

        Veiculo veiculo = veiculoRepository.findByPlaca(entradaRequestDTO.getPlaca()).orElseGet(() ->
            new Veiculo(entradaRequestDTO.getPlaca()));

        veiculoRepository.save(veiculo);

        Vaga vaga = vagaRepository.findById(entradaRequestDTO.getIdVaga()).orElseThrow(() ->
            new GenericException("Vaga não encontrada"));

        vaga.setOcupada(true);
        vagaRepository.save(vaga);

        // Calcula o preço base ajustado conforme regra de preço dinâmico
        Double precoAjustado = calcularPrecoDinamico(setor.getPrecoBase(), lotacao);

        // Cria sessão salvando preçoBase ajustado para usar na cobrança posterior
        SessaoEstacionamento sessao = new SessaoEstacionamento();

        sessao.setId(null);
        sessao.setVeiculo(veiculo);
        sessao.setVaga(vaga);
        sessao.setSetor(setor);
        sessao.setHrEntrada(LocalDateTime.now());
        sessao.setAtiva(true);
        sessao.setPreco(precoAjustado); // novo campo na entidade SessaoEstacionamento

        sessaoRepository.save(sessao);
    }

    private Double calcularPrecoDinamico(Double precoBase, Double lotacao) {
        if(lotacao < 0.25) {
            return precoBase * 0.9;      // desconto 10%
        }else if(lotacao <= 0.5) {
            return precoBase;            // 0% desconto
        }else if(lotacao <= 0.75){
            return precoBase * 1.1;      // aumenta 10%
        }else {
            return precoBase * 1.25;     // aumenta 25%
        }
    }
}