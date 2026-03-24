package br.com.cotiinformatica.repositories;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.factories.ConnectionFactory;

import java.sql.Statement;

public class ProdutoRepository extends BaseRepository<Produto>{

    @Override
    public int criar(Produto produto) throws Exception {
        try(var connection = ConnectionFactory.getConnection()){

            var query = """
                    INSERT INTO PRODUTOS(NOME, PRECO)
                    VALUES(?,?)
                    """;

            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.execute();

            var result = statement.getGeneratedKeys();
            if(result.next()){
                return result.getInt(1);
            }
            else {
                return 0;
            }
        }
    }
}
