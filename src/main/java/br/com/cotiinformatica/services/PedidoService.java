package br.com.cotiinformatica.services;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.ClienteRepository;
import br.com.cotiinformatica.repositories.PedidoRepository;
import br.com.cotiinformatica.repositories.ProdutoRepository;
import java.util.List;

public class PedidoService {

    public void criarPedido(Pedido pedido, Cliente cliente, List<Produto> produtos){

        var clienteRepository = new ClienteRepository();
        var produtoRepository = new ProdutoRepository();
        var pedidoRepository = new PedidoRepository();

        try{

            if(produtos.isEmpty()){
                System.out.println("\nNão há produtos adicionados nesse pedido.");
                return;
            }

          if(clienteRepository.emailExistente(cliente.getEmail())){
              System.out.println("\nO email informado já está cadastrado.");
              return;
          }

          var idCliente = clienteRepository.criar(cliente);
          cliente.setId(idCliente);

          for(var produto: produtos){
              var idProduto = produtoRepository.criar(produto);
              produto.setId(idProduto);
          }

          pedido.setCliente(cliente);
          pedido.setProdutos(produtos);

          var idPedido = pedidoRepository.criar(pedido);
          pedido.setId(idPedido);

            System.out.println("\nPedido cadastrado com sucesso: " + pedido.getId());
        }
        catch (Exception e){
            System.out.println("\nFalha: " + e.getMessage());
        }

    }

}
