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
import br.edu.ifsc.fln.model.dao.CorDAO;
import br.edu.ifsc.fln.model.dao.MarcaDAO;
import br.edu.ifsc.fln.model.dao.ModeloDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.Cor;
import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.domain.Modelo;
import br.edu.ifsc.fln.model.domain.Veiculo;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Riffen
 */
public class FXMLAnchorPaneCadastroVeiculoDialogController implements Initializable {

    @FXML
    private TextField tfPlaca;
    
    @FXML
    private TextField tfObservacoes;
    
    @FXML
    private ComboBox<Modelo> cbModelo;
    
    @FXML
    private ComboBox<Cor> cbCor;
    
    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;
    
//    private List<Categoria> listaCategorias;
//    private ObservableList<Categoria> observableListCategorias;
        
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ModeloDAO modeloDAO = new ModeloDAO();
    private final CorDAO corDAO = new CorDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Veiculo veiculo; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modeloDAO.setConnection(connection);
        corDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        
        carregarComboBoxModelos();
        carregarComboBoxCores();
        carregarComboBoxClientes();
        
        setFocusLostHandle();
    } 
    
    private void setFocusLostHandle() {
        tfPlaca.focusedProperty().addListener((ov, oldV, newV) -> {
        if (!newV) { // focus lost
                if (tfPlaca.getText() == null || tfPlaca.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    tfPlaca.requestFocus();
                }
            }
        });
    }
    
//This works fine too:    
//root.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//    focusState(newValue);
//});
//
//private void focusState(boolean value) {
//    if (value) {
//        System.out.println("Focus Gained");
//    }
//    else {
//        System.out.println("Focus Lost");
//    }
//} 
    
    private List<Modelo> listaModelos;
    private ObservableList<Modelo> observableListModelos; 
    
    public void carregarComboBoxModelos() {
        listaModelos = modeloDAO.listar();
        observableListModelos = 
                FXCollections.observableArrayList(listaModelos);
        cbModelo.setItems(observableListModelos);
    }   
    
    private List<Cor> listaCores;
    private ObservableList<Cor> observableListCores; 
    
    public void carregarComboBoxCores() {
        listaCores = corDAO.listar();
        observableListCores = 
                FXCollections.observableArrayList(listaCores);
        cbCor.setItems(observableListCores);
    } 
    
    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes; 
    public void carregarComboBoxClientes() {
                listaClientes = clienteDAO.listar();
        observableListClientes = 
                FXCollections.observableArrayList(listaClientes);
        cbCliente.setItems(observableListClientes);
    }
    
    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmarClicked
     */
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    /**
     * @param buttonConfirmarClicked the buttonConfirmarClicked to set
     */
    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    /**
     * @return the produto
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param produto the produto to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        tfPlaca.setText(veiculo.getPlaca());
        tfObservacoes.setText(veiculo.getObservacoes());
        cbModelo.getSelectionModel().select(veiculo.getModelo());
        cbCor.getSelectionModel().select(veiculo.getCor());
        cbCliente.getSelectionModel().select(veiculo.getCliente());
    }    
    
    @FXML
    private void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            veiculo.setPlaca(tfPlaca.getText());
            veiculo.setObservacoes(tfObservacoes.getText());
            veiculo.setModelo(cbModelo.getSelectionModel().getSelectedItem());
            veiculo.setCor(cbCor.getSelectionModel().getSelectedItem());
            veiculo.setCliente(cbCliente.getSelectionModel().getSelectedItem());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleBtCancelar() {
        dialogStage.close();
    }
    
        //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        
        if (tfPlaca.getText() == null || tfPlaca.getText().isEmpty()) {
            errorMessage += "Placa inv�lida!\n";
        }
        
        if (tfObservacoes.getText() == null || tfObservacoes.getText().isEmpty()) {
            errorMessage += "Observa��es inv�lidas!\n";
        }
        
        if (cbModelo.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione um modelo!\n";
        }
        
       if (cbCor.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione uma cor!\n";
        }
       
        if (cbCliente.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione um cliente!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campo(s) inv�lido(s), por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
   
}
