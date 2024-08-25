package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Categoria;
import br.edu.ifsc.fln.model.domain.ECategoria;
import br.edu.ifsc.fln.model.domain.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Servico servico) {
        String sql = "INSERT INTO servico(descricao, valor, pontos, categoria_id) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getValor());
            stmt.setInt(3, servico.getPontos());
            stmt.setInt(4, servico.getCategoria());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Servico servico) {
        String sql = "UPDATE servico SET descricao=?, valor=?, pontos=?, categoria_id=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getValor());
            stmt.setInt(3, servico.getPontos());
            stmt.setInt(4, servico.getCategoria().ordinal());
            stmt.setInt(5, servico.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void remover(Servico servico) {
        String sql = "DELETE FROM servico WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Servico> listar() {
        String sql = "SELECT * FROM servico";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = populateVO(resultado);
                retorno.add(servico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Servico buscar(Servico servico) {
        String sql = "SELECT * FROM servico WHERE id=?";
        Servico retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private Servico populateVO(ResultSet rs) throws SQLException {
        Servico servico = new Servico();
        servico.setId(rs.getInt("id"));
        servico.setDescricao(rs.getString("descricao"));
        servico.setValor(rs.getDouble("valor"));
        return servico;
    }
}
