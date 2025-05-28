package br.com.calife.estacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vagas")
public class Vaga {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = { CascadeType.DETACH})
    @JoinColumn(name = "idSetor")
    private Setor setor;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "ocupada", nullable = false)
    private Boolean ocupada;
}
