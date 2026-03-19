package br.com.cotiinformatica.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cliente {

    private Integer id;
    private String nome;
    private String email;
    private List<Pedido> pedidos;

}
