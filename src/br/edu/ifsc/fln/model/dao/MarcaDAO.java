package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que fornece opera��es de acesso a dados para o modelo {@link Marca}.
 * Esta classe cont�m m�todos para inserir, alterar, remover, listar e buscar marcas no banco de dados.
 * 
 * <br><br> @author <b> andreriffen </b>
 * 
 * @author andreriffen
 */
public class MarcaDAO {

    private Connection connection;

    /**
     * Obt�m a conex�o com o banco de dados.
     * 
     * @return A conex�o com o banco de dados.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Define a conex�o com o banco de dados.
     * 
     * @param connection A conex�o com o banco de dados a ser definida.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insere uma nova marca no banco de dados.
     * 
     * @param marca A marca a ser inserida.
     * @return {@code true} se a inser��o for bem-sucedida, {@code false} caso contr�rio.
     */
    public boolean inserir(Marca marca) {
        // String sql = "INSERT INTO marca(nome, obs, valor) VALUES(?, ?, ?)";
        String sql = "INSERT INTO marca(nome) VALUES(?)";        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca.getNome());
            // stmt.setString(2, marca.getObs());
            // stmt.setBigDecimal(3, marca.getValor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Atualiza os dados de uma marca no banco de dados.
     * 
     * @param marca A marca com os dados atualizados.
     * @return {@code true} se a atualiza��o for bem-sucedida, {@code false} caso contr�rio.
     */
    public boolean alterar(Marca marca) {
        String sql = "UPDATE marca SET nome=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Remove uma marca do banco de dados.
     * 
     * @param marca A marca a ser removida.
     * @return {@code true} se a remo��o for bem-sucedida, {@code false} caso contr�rio.
     */
    public boolean remover(Marca marca) {
        String sql = "DELETE FROM marca WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, marca.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Lista todas as marcas no banco de dados.
     * 
     * @return Uma lista contendo todas as marcas.
     */
    public List<Marca> listar() {
        String sql = "SELECT * FROM marca";
        List<Marca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Marca marca = new Marca();
                marca.setId(resultado.getInt("id"));
                marca.setNome(resultado.getString("nome"));
                retorno.add(marca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Busca uma marca no banco de dados pelo ID.
     * 
     * @param marca A marca com o ID a ser buscado.
     * @return A marca encontrada ou uma marca com valores padr�o se n�o encontrada.
     */
    public Marca buscar(Marca marca) {
        String sql = "SELECT * FROM marca WHERE id=?";
        Marca retorno = new Marca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, marca.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                marca.setNome(resultado.getString("nome"));
                retorno = marca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
