package br.com.calife.estacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SessaoEstacionamento")
public class SessaoEstacionamento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = { CascadeType.DETACH})
    @JoinColumn(name = "idVaga")
    private Vaga vaga;


    @ManyToOne(cascade = { CascadeType.DETACH})
    @JoinColumn(name = "idVeiculo")
    private Veiculo veiculo;

    @ManyToOne(cascade = { CascadeType.DETACH})
    @JoinColumn(name = "idSetor")
    private Setor setor;

    @JoinColumn(name = "hrEntrada")
    private LocalDateTime hrEntrada;

    @JoinColumn(name = "hrSaida")
    private LocalDateTime hrSaida;

    @JoinColumn(name = "preco")
    private Double preco;

    @JoinColumn(name = "precoBase")
    private Double precoBase;

    @JoinColumn(name = "ativa")
    private Boolean ativa;
}