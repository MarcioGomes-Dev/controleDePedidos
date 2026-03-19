package br.com.cotiinformatica.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class Produto {

    private Integer id;
    private String nome;
    private Double preco;
    private List<Pedido> pedidos;
}
