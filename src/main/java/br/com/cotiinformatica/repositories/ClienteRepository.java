package br.com.cotiinformatica.repositories;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.factories.ConnectionFactory;

import java.sql.Statement;

public class ClienteRepository extends BaseRepository<Cliente>{

    @Override
    public int criar(Cliente cliente) throws Exception {

        try(var connection = ConnectionFactory.getConnection()){

            var query = """
                    INSERT INTO CLIENTES(NOME, EMAIL)
                    VALUES(?,?)
                    """;

            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.execute();

            var result = statement.getGeneratedKeys();
            if(result.next()) {
                return result.getInt(1);
            }
            else{
                return 0;
            }
        }
    }

    public boolean emailExistente(String email) throws Exception{

        try (var connection = ConnectionFactory.getConnection()) {

            var query = """
                    SELECT COUNT(*) FROM CLIENTES
                    WHERE EMAIL = ?
                    """;

            var statement = connection.prepareStatement(query);
            statement.setString(1, email);
            var result = statement.executeQuery();

            if(result.next()){
                return result.getInt(1) > 0;
            }
            else {
                return false;
            }
        }
    }


}
