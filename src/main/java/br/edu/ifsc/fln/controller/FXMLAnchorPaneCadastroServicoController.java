/*
 * The MIT License
 *
 * Copyright 2024 Riffen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ServicoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Servico;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Riffen
 */
public class FXMLAnchorPaneCadastroServicoController implements Initializable {

    
    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btInserir;

    @FXML
    private Label lbServicoCategoria;

    @FXML
    private Label lbServicoDescricao;

    @FXML
    private Label lbServicoId;

    @FXML
    private Label lbServicoPontos;

    @FXML
    private Label lbServicoValor;

    @FXML
    private TableColumn<Servico, String> tableColumnServicoDescricao;

    @FXML
    private TableView<Servico> tableViewServicos;
    
    private List<Servico> listaServicos;
    private ObservableList<Servico> observableListServicos;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        carregarTableViewServico();
        
        tableViewServicos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewServicos(newValue)); //lambda ->
    }     
    
    public void carregarTableViewServico() {
        tableColumnServicoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        listaServicos = servicoDAO.listar();
        
        observableListServicos = FXCollections.observableArrayList(listaServicos);
        tableViewServicos.setItems(observableListServicos);
    }
    
    public void selecionarItemTableViewServicos(Servico servico) {
        if (servico != null) {
            lbServicoId.setText(String.valueOf(servico.getId())); 
            lbServicoDescricao.setText(servico.getDescricao());
            lbServicoValor.setText(String.valueOf(servico.getValor()));
            lbServicoPontos.setText(String.valueOf(servico.getPontos()));
            lbServicoCategoria.setText(servico.getCategoria().name());
        } else {
            lbServicoId.setText(""); 
            lbServicoDescricao.setText("");
            lbServicoValor.setText("");
            lbServicoPontos.setText("");
            lbServicoCategoria.setText("");
        }
        
    }
    
    @FXML
    public void handleBtInserir() throws IOException {
        Servico servico = new Servico();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroServicoDialog(servico);
        if (btConfirmarClicked) {
            servicoDAO.inserir(servico);
            carregarTableViewServico();
        } 
    }
    
    @FXML 
    public void handleBtAlterar() throws IOException {
        Servico servico = tableViewServicos.getSelectionModel().getSelectedItem();
        if (servico != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroServicoDialog(servico);
            if (btConfirmarClicked) {
                servicoDAO.alterar(servico);
                carregarTableViewServico();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o \nde uma Cor na tabela ao lado");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtExcluir() throws IOException {
        Servico servico = tableViewServicos.getSelectionModel().getSelectedItem();
        if (servico != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Excluir Servi�o");
            alert.setContentText("Deseja realmente excluir esse servi�o ?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK)
            {
                servicoDAO.remover(servico);
                carregarTableViewServico();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o \nde um Servi�o na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroServicoDialog(Servico servico) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroServicoController.class.getResource("/view/FXMLAnchorPaneCadastroServicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //criação de um est�gio de di�logo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Servico");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //enviando o obejto categoria para o controller
        FXMLAnchorPaneCadastroServicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setServico(servico);
        
        //apresenta o di�logo e aguarda a confirma��o do usu�rio
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
    
}
