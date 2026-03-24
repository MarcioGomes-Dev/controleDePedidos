package br.com.cotiinformatica.controllers;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.services.PedidoService;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class PedidoController {

    public void capturarPedido() {

        Faker faker = new Faker(new Locale("pt-BR"));

        Cliente cliente = new Cliente();
        cliente.setNome(faker.name().fullName());
        cliente.setEmail(faker.internet().emailAddress());

        var produtos = new ArrayList<Produto>();

        for (int i = 1; i <= 3; i++) {
            Produto produto = new Produto();
            produto.setNome(faker.commerce().productName());
            produto.setPreco(Double.parseDouble(faker.commerce().price().replace(",", ".")));

            produtos.add(produto);
        }

        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        pedido.setValor(valorTotal);

        //Enviar para a camada de serviços criar o pedido
        var pedidoService = new PedidoService();
        pedidoService.criarPedido(pedido, cliente, produtos);
    }
}
