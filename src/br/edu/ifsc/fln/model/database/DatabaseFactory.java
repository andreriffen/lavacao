package br.edu.ifsc.fln.model.database;

/**
 * Utiliza o design pattern do tipo <code>Factory</code> para conectar com o banco de dados.
 * Fornece m�todos est�ticos para criar inst�ncias espec�ficas da interface {@link Database}.<br><br>
 * 
 * Refatorado para apenas: <b><a href="https://mvnrepository.com/artifact/org.postgresql/postgresql"> JDBC-R MySQL</a></b><br>
 * <i>Removido: <a href="https://mvnrepository.com/artifact/mysql/mysql-connector-java"> JDBC-R Postgre SQL</a></i><br> 
 * 
 * @author andreriffen
 */
public class DatabaseFactory {

    /**
     * Cria uma inst�ncia de {@link Database} para MySQL.
     * 
     * @param nome O nome do banco de dados para o qual se deseja obter uma inst�ncia de {@link Database}.
     *             Aceita "mysql" para MySQL.
     * @return Uma inst�ncia de {@link Database} correspondente ao MySQL.
     *         Retorna <code>null</code> se o nome do banco de dados n�o corresponder a "mysql".
     */
    public static Database getDatabase(String nome) {
        if (nome.equals("mysql")) {
            return new DatabaseMySQL();
        }
        return null;
    }
}
