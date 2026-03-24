package br.com.cotiinformatica.repositories;

import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.factories.ConnectionFactory;

import java.sql.Statement;
import java.sql.Timestamp;

public class PedidoRepository extends BaseRepository<Pedido>{

    @Override
    public int criar(Pedido pedido) throws Exception {

        try (var connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            var query = """
                    INSERT INTO PEDIDOS(DATAHORA, VALOR, CLIENTE_ID)
                    VALUES(?,?,?)
                    """;
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, Timestamp.valueOf(pedido.getDataHora()));
            statement.setDouble(2, pedido.getValor());
            statement.setInt(3, pedido.getCliente().getId());
            statement.execute();

            var result = statement.getGeneratedKeys();
            if (result.next()) {
                pedido.setId(result.getInt(1));
            }

            query = """
                    INSERT INTO PEDIDOS_PRODUTOS(PEDIDO_ID, PRODUTO_ID)
                    VALUES(?, ?)
                    """;
            for (var produto : pedido.getProdutos()){

                statement = connection.prepareStatement(query);
                statement.setInt(1, pedido.getId());
                statement.setInt(2, produto.getId());
                statement.execute();
            }

            connection.commit();
        }

        return pedido.getId();
    }
}
