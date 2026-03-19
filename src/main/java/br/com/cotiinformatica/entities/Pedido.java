package br.com.cotiinformatica.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;
@Getter
@Setter
public class Pedido {

    private Integer id;
    private LocalDateTime dataHora;
    private Double valor;
    private Cliente cliente;
    private List<Produto> produtos;
}
