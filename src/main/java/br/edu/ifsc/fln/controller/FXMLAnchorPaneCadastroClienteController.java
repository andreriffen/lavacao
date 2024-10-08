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

import br.edu.ifsc.fln.model.dao.ClienteDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaFisica;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceDialog;
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
public class FXMLAnchorPaneCadastroClienteController implements Initializable {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btInserir;

    @FXML
    private Label lbClienteCelular;

    @FXML
    private Label lbClienteDataCadastro;

    @FXML
    private Label lbClienteEmail;

    @FXML
    private Label lbClienteId;

    @FXML
    private Label lbClienteInscricaoEstadual;
    
    @FXML
    private Label lbPlaceholderInscricao;

    @FXML
    private Label lbClienteNome;

    @FXML
    private Label lbClienteTipo;

    @FXML
    private Label lbCliente_CPF_CNPJ;

    @FXML
    private Label lbClienteDataNascimento;
    
    @FXML
    private Label lbClienteEndereco;

    @FXML
    private TableColumn<Cliente, String>  tableColumnClienteCelular;

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;

    @FXML
    private TableView<Cliente> tableViewClientes;

    
    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }     
    
    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        
        listaClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente) {
        if (cliente != null) {
            lbClienteId.setText(String.valueOf(cliente.getId())); 
            lbClienteNome.setText(cliente.getNome());
            lbClienteCelular.setText(cliente.getCelular());
            lbClienteEmail.setText(cliente.getEmail());
            lbClienteEndereco.setText(cliente.getEndereco());
            lbClienteDataCadastro.setText(String.valueOf(
                    cliente.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            
            if (cliente instanceof PessoaFisica) 
            {
               lbClienteDataNascimento.setText(String.valueOf(
                    ((PessoaFisica) cliente).getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
               lbClienteTipo.setText("Pessoa F��sica");
               lbCliente_CPF_CNPJ.setText(((PessoaFisica) cliente).getCpf());
               lbClienteInscricaoEstadual.setText("");
               lbPlaceholderInscricao.setText("");
            }
            else
            {
                 lbClienteDataNascimento.setText(String.valueOf(
                    ((PessoaJuridica) cliente).getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
               lbClienteTipo.setText("Pessoa Jur��dica");
               lbCliente_CPF_CNPJ.setText(((PessoaJuridica)cliente).getCnpj());
               lbClienteInscricaoEstadual.setText(((PessoaJuridica)cliente).getInscricaoEstadual());
               lbPlaceholderInscricao.setText("Inscri��o Estadual");
            }
        } else {
            lbClienteId.setText(""); 
            lbClienteNome.setText("");
            lbClienteCelular.setText("");
            lbClienteEmail.setText("");
            lbClienteEndereco.setText("");
            lbClienteDataCadastro.setText("");
            lbClienteDataNascimento.setText("");
            lbClienteTipo.setText("");
            lbCliente_CPF_CNPJ.setText("");
            lbClienteInscricaoEstadual.setText("");
            lbPlaceholderInscricao.setText("Inscri��o Estadual");
        }
        
    }
    
    @FXML
    public void handleBtInserir() throws IOException {
        Cliente cliente = getTipoCliente();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(cliente);
        if (btConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        } 
    }
    
     private Cliente getTipoCliente() {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Pessoa F��sica");
        opcoes.add("Pessoa Jur��dica");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Pessoa F��sica", opcoes);
        dialog.setTitle("Di�logo de Op��es");
        dialog.setHeaderText("Escolha o tipo de cliente");
        dialog.setContentText("Tipo de cliente: ");
        Optional<String> escolha = dialog.showAndWait();
        if (escolha.isPresent()) {
            if (escolha.get().equalsIgnoreCase("Pessoa F��sica")) 
                return new PessoaFisica();
            else 
                return new PessoaJuridica();
        } else {
            return null;
        }
    }
    
    @FXML 
    public void handleBtAlterar() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(cliente);
            if (btConfirmarClicked) {
                clienteDAO.alterar(cliente);
                carregarTableViewCliente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o \nde um Cliente na tabela ao lado");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtExcluir() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Excluir Cliente");
            alert.setContentText("Deseja realmente excluir esse cliente ?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK)
            {
                clienteDAO.remover(cliente);
                carregarTableViewCliente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o \nde um Cliente na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroClienteDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroClienteController.class.getResource("/view/FXMLAnchorPaneCadastroClienteDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //criação de um est�gio de di�logo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //enviando o obejto cliente para o controller
        FXMLAnchorPaneCadastroClienteDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        
        //apresenta o di�logo e aguarda a confirma��o do usu�rio
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
    
}
