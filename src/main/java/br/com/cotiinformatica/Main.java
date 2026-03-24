package br.com.cotiinformatica;

import br.com.cotiinformatica.controllers.PedidoController;

public class Main {
   public static void main(String[] args) {

        var pedidoController = new PedidoController();
        pedidoController.capturarPedido();
   }
}