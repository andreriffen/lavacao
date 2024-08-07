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
 * Classe que fornece operações de acesso a dados para o modelo {@link Cliente}.
 * Esta classe contém métodos para inserir, alterar, remover, listar e buscar clientes no banco de dados.
 *  
 * <br><br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
public class ClienteDAO {

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
     * Insere um novo cliente no banco de dados.
     * 
     * @param cliente O cliente a ser inserido.
     * @return {@code true} se a inserção foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, cpf, telefone, endereco, data_nascimento) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setDate(5, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Altera os dados de um cliente existente no banco de dados.
     * 
     * @param cliente O cliente com os dados atualizados.
     * @return {@code true} se a alteração foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, telefone=?, endereco=?, data_nascimento=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setDate(5, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setInt(6, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Remove um cliente do banco de dados.
     * 
     * @param cliente O cliente a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Lista todos os clientes do banco de dados.
     * 
     * @return Uma lista contendo todos os clientes.
     */
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = populateVO(resultado);
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Busca um cliente pelo seu identificador.
     * 
     * @param cliente O cliente com o identificador a ser buscado.
     * @return O cliente encontrado ou um cliente vazio se não encontrado.
     */
    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM cliente WHERE id=?";
        Cliente retorno = new Cliente();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     * Popula um objeto {@link Cliente} a partir dos dados de um {@link ResultSet}.
     * 
     * @param rs O {@link ResultSet} contendo os dados do cliente.
     * @return O cliente populado com os dados do {@link ResultSet}.
     * @throws SQLException Se ocorrer um erro ao acessar os dados do {@link ResultSet}.
     */
    private Cliente populateVO(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return cliente;
    }
}
