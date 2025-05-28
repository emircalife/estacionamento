package br.com.calife.estacionamento.domain.service;

import br.com.calife.estacionamento.core.exception.GenericException;
import br.com.calife.estacionamento.domain.entity.DTO.SaidaRequestDTO;
import br.com.calife.estacionamento.domain.entity.SessaoEstacionamento;
import br.com.calife.estacionamento.domain.entity.Vaga;
import br.com.calife.estacionamento.domain.repository.SessaoEstacionamentoRepository;
import br.com.calife.estacionamento.domain.repository.SetorRepository;
import br.com.calife.estacionamento.domain.repository.VagaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class SaidaService {
    @Autowired
    private final VagaRepository vagaRepository;

    @Autowired
    private final SessaoEstacionamentoRepository sessaoRepository;

    @Autowired
    private final SetorRepository setorRepository;


    public Double registrarSaida(SaidaRequestDTO saidaRequestDTO) {
        SessaoEstacionamento sessao = sessaoRepository.findByVeiculoPlacaAndAtiva(saidaRequestDTO.getPlacaVeiculo(), true).orElseThrow(() ->
                new GenericException("Sessão ativa não encontrada para o veículo."));

        sessao.setHrSaida(LocalDateTime.now());

        Long duracaoMinutos = Duration.between(sessao.getHrEntrada(), sessao.getHrSaida()).toMinutes();

        Double preco = calcularPrecoComRegra(sessao.getPrecoBase(), duracaoMinutos);

        sessao.setPreco(preco); // Atualiza o preco
        sessao.setAtiva(false);
        sessaoRepository.save(sessao);

        Vaga vaga = sessao.getVaga();
        vaga.setOcupada(false);
        vagaRepository.save(vaga);

        return preco;
    }

    private Double calcularPrecoComRegra(Double precoBase, Long duracaoMinutos) {
        // Carência de 15 minutos - sem cobrança
        if (duracaoMinutos <= 15) {
            return 0.0;
        }

        // Primeira hora: 100% do preço base
        if (duracaoMinutos <= 60) {
            return precoBase;
        }

        // Após a primeira hora, cobrar em intervalos de 15 minutos
        long minutosExtras = duracaoMinutos - 60;
        int blocos15Minutos = (int)Math.ceil(minutosExtras / 15.0);

        double precoExtras = (precoBase * 0.25 * blocos15Minutos);  // cada intervalo de 15min = 25% do preço base (1h)

        return precoBase + precoExtras;
    }
}
