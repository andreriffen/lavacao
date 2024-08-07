package br.edu.ifsc.fln.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação da interface {@link Database} para conexões com o banco de dados MySQL.
 * 
 * @author andreriffen
 */
public class DatabaseMySQL implements Database {

    private Connection connection;

    /**
     * Estabelece uma conexão com o banco de dados MySQL.
     * 
     * @return A conexão estabelecida com o banco de dados MySQL.
     *         Retorna <code>null</code> se ocorrer uma falha na conexão.
     */
    @Override
    public Connection conectar() {
        try {
            final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver para MySQL 8
            final String URL = "jdbc:mysql://localhost:3306/db_lavacao?useTimezone=true&serverTimezone=UTC"; // URL de conexão para MySQL 8
            final String USER = "root";
            final String PASS = "";
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão realizada com sucesso!");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha na conexão com o banco de dados.");
            return null;
        }
    }

    /**
     * Fecha a conexão com o banco de dados MySQL.
     * 
     * @param connection A conexão a ser fechada.
     */
    @Override
    public void desconectar(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
