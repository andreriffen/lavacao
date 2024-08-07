package br.edu.ifsc.fln.model.database;

import java.sql.Connection;

/**
 * Interface para operações de conexão com o banco de dados.
 * Define métodos para estabelecer e encerrar conexões com o banco de dados.
 * 
 * @author andreriffen
 */
public interface Database {
    
    /**
     * Estabelece uma conexão com o banco de dados.
     * 
     * @return A conexão estabelecida com o banco de dados.
     */
    public Connection conectar();

    /**
     * Encerra a conexão com o banco de dados.
     * 
     * @param conn A conexão a ser encerrada.
     */
    public void desconectar(Connection conn);
}
