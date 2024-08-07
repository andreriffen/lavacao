package br.edu.ifsc.fln.model.database;

/**
 * Utiliza o design pattern do tipo <code>Factory</code> para conectar com o banco de dados.
 * Fornece métodos estáticos para criar instâncias específicas da interface {@link Database}.<br><br>
 * 
 * Refatorado para apenas: <b><a href="https://mvnrepository.com/artifact/org.postgresql/postgresql"> JDBC-R MySQL</a></b><br>
 * <i>Removido: <a href="https://mvnrepository.com/artifact/mysql/mysql-connector-java"> JDBC-R Postgre SQL</a></i><br> 
 * 
 * @author andreriffen
 */
public class DatabaseFactory {

    /**
     * Cria uma instância de {@link Database} para MySQL.
     * 
     * @param nome O nome do banco de dados para o qual se deseja obter uma instância de {@link Database}.
     *             Aceita "mysql" para MySQL.
     * @return Uma instância de {@link Database} correspondente ao MySQL.
     *         Retorna <code>null</code> se o nome do banco de dados não corresponder a "mysql".
     */
    public static Database getDatabase(String nome) {
        if (nome.equals("mysql")) {
            return new DatabaseMySQL();
        }
        return null;
    }
}
