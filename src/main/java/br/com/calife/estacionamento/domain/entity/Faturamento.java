package br.com.calife.estacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faturamentos")
public class Faturamento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "setor")
    private String setor;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "valor")
    private Double valor;
}