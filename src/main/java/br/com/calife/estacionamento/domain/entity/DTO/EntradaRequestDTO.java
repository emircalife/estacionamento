package br.com.calife.estacionamento.domain.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntradaRequestDTO {
    String placa;
    Integer idVaga;
    Integer idSetor;

    public EntradaRequestDTO() {

    }
}
