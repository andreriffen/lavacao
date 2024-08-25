package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que fornece operações de acesso a dados para o modelo {@link Modelo}.
 * Esta classe contém métodos para inserir, alterar, remover, listar e buscar modelos no banco de dados.
 *
 * @author andreriffen
 */
public class ModeloDAO {

    private Connection connection;

    /**
     * Obtém a conexão com o banco de dados.
     * 
     * @return A conexão com o banco de dados.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Define a conexão com o banco de dados.
     * 
     * @param connection A conexão com o banco de dados.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insere um novo modelo no banco de dados.
     * 
     * @param modelo O modelo a ser inserido.
     * @return {@code true} se a inserção foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean inserir(Modelo modelo) {
        String sql = "INSERT INTO modelo(descricao) VALUES(?)";
      //String sql = "INSERT INTO modelo(descricao, marca_id, motor_id, categoria) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo.getDescricao());
            //stmt.setInt(2, modelo.getMarca().getId());
            //stmt.setInt(3, modelo.getMotor().getId());
            //stmt.setString(4, modelo.getCategoria().name());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Altera os dados de um modelo existente no banco de dados.
     * 
     * @param modelo O modelo com os dados atualizados.
     * @return {@code true} se a alteração foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean alterar(Modelo modelo) {
        String sql = "UPDATE modelo SET descricao=? WHERE id=?";
      //String sql = "UPDATE modelo SET descricao=?, marca_id=?, motor_id=?, categoria=? WHERE id=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(2, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Remove um modelo do banco de dados.
     * 
     * @param modelo O modelo a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean remover(Modelo modelo) {
        String sql = "DELETE FROM modelo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Lista todos os modelos do banco de dados.
     * 
     * @return Uma lista contendo todos os modelos.
     */
    public List<Modelo> listar() {
        String sql = "SELECT * FROM modelo";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Busca um modelo pelo seu identificador.
     * 
     * @param modelo O modelo com o identificador a ser buscado.
     * @return O modelo encontrado ou um modelo vazio se não encontrado.
     */
    public Modelo buscar(Modelo modelo) {
        String sql = "SELECT * FROM modelo WHERE id=?";
        Modelo retorno = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     * Popula um objeto {@link Modelo} a partir dos dados de um {@link ResultSet}.
     * 
     * @param rs O {@link ResultSet} contendo os dados do modelo.
     * @return O modelo populado com os dados do {@link ResultSet}.
     * @throws SQLException Se ocorrer um erro ao acessar os dados do {@link ResultSet}.
     */
    private Modelo populateVO(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();
        modelo.setId(rs.getInt("id"));
        modelo.setDescricao(rs.getString("descricao"));

        //Marca marca = new Marca();
        //marca.setId(rs.getInt("marca_id"));
        //modelo.setMarca(marca);
        
        //Motor motor = new Motor();
        //motor.setId(rs.getInt("motor_id"));
        //modelo.setMotor(motor);

        //modelo.setCategoria(ECategoria.valueOf(rs.getString("categoria")));
        return modelo;
    }
}
