package br.com.calife.estacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "setores")
public class Setor {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "precoBase")
    private Double precoBase;

    @Column(name = "capacidadeMaxima")
    private int capacidadeMaxima;

    @Column(name = "horarioAbertura")
    private LocalTime horarioAbertura;

    @Column(name = "limiteDuracaoMinutos")
    private int limiteDuracaoMinutos;
}
