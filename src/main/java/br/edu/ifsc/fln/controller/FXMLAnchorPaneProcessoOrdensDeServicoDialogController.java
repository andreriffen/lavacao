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
import br.edu.ifsc.fln.model.dao.ServicoDAO;
import br.edu.ifsc.fln.model.dao.VeiculoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.EStatus;
import br.edu.ifsc.fln.model.domain.ItemOS;
import br.edu.ifsc.fln.model.domain.OrdemServico;
import br.edu.ifsc.fln.model.domain.Servico;
import br.edu.ifsc.fln.model.domain.Veiculo;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Riffen
 */
public class FXMLAnchorPaneProcessoOrdensDeServicoDialogController implements Initializable {

    @FXML
    private Button buttonAdicionar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private ComboBox<Servico> comboBoxServico;

    @FXML
    private ComboBox<Veiculo> comboBoxVeiculos;

    @FXML
    private MenuItem contextMenuItemAtualizarObs;

    @FXML
    private MenuItem contextMenuItemRemoverItem;

    @FXML
    private ContextMenu contextMenuTableView;

    @FXML
    private DatePicker datePickerData;

    @FXML
    private TableColumn<ItemOS, String> tableColumnObservacoes;

    @FXML
    private TableColumn<ItemOS, Servico> tableColumnServico;

    @FXML
    private TableColumn<ItemOS, BigDecimal> tableColumnValor;

    @FXML
    private TableView<ItemOS> tableViewItensDeVenda;

    @FXML
    private TextField textFieldDesconto;

    @FXML
    private TextField textFieldValor;
    
    @FXML
    private TextField textFieldObservacoesServico;
    

    private List<Veiculo> listaVeiculos;
    private List<Servico> listaServicos;
    private ObservableList<Veiculo> observableListVeiculos;
    private ObservableList<Servico> observableListServicos;
    private ObservableList<ItemOS> observableListItensOS;

    //atributos para manipula��o de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private OrdemServico ordemServico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        veiculoDAO.setConnection(connection);
        servicoDAO.setConnection(connection);
        carregarComboBoxVeiculos();
        carregarComboBoxServicos();
        carregarChoiceBoxSituacao();
        setFocusLostHandle();
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valorServico"));
    }

    private void carregarComboBoxVeiculos() {
        listaVeiculos = veiculoDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listaVeiculos);
        comboBoxVeiculos.setItems(observableListVeiculos);
    }

    private void carregarComboBoxServicos() {
        listaServicos = servicoDAO.listar();
        observableListServicos = FXCollections.observableArrayList(listaServicos);
        comboBoxServico.setItems(observableListServicos);
    }
    
    
    public void carregarChoiceBoxSituacao() {
        choiceBoxStatus.setItems( FXCollections.observableArrayList(EStatus.values()));
        choiceBoxStatus.getSelectionModel().select(0);
    }

    private void setFocusLostHandle() {
        textFieldDesconto.focusedProperty().addListener((ov, oldV, newV) -> {
        if (!newV) { // focus lost
                if (textFieldDesconto.getText() != null && !textFieldDesconto.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    ordemServico.setDesconto(Double.parseDouble(textFieldDesconto.getText()));
                    textFieldValor.setText(ordemServico.getTotal().toString());
                    
                }
            }
        });
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
     * @return the venda
     */
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    /**
     * @param venda the venda to set
     */
    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
        if (ordemServico.getNumero()!= 0) { 
            comboBoxVeiculos.getSelectionModel().select(this.ordemServico.getVeiculo());
            datePickerData.setValue(this.ordemServico.getAgenda());
            observableListItensOS = FXCollections.observableArrayList(
                    this.ordemServico.getItensOS());
            tableViewItensDeVenda.setItems(observableListItensOS);
            textFieldValor.setText(String.format("%.2f", this.ordemServico.getTotal()));
            textFieldDesconto.setText(String.format("%.2f", this.ordemServico.getDesconto()));
            choiceBoxStatus.getSelectionModel().select(this.ordemServico.getStatus());

        }
    }

    @FXML
    public void handleButtonAdicionar() {
        Servico servico;
        ItemOS itemOS = new ItemOS();
        if (comboBoxServico.getSelectionModel().getSelectedItem() != null) {
            //o comboBox possui dados sintetizados de Servi�o para evitar carga desnecess�ria de informa��o
            servico = comboBoxServico.getSelectionModel().getSelectedItem();
            //a instru��o a seguir busca detalhes do servi�o selecionado
            servico = servicoDAO.buscar(servico);
                
            itemOS.setServico(servico); 
            itemOS.setObservacoes(textFieldObservacoesServico.getText());
            itemOS.setValorServico(BigDecimal.valueOf(servico.getValor()));
            itemOS.setOrdemServico(ordemServico);
            ordemServico.getItensOS().add(itemOS);
            observableListItensOS = FXCollections.observableArrayList(ordemServico.getItensOS());
            tableViewItensDeVenda.setItems(observableListItensOS);
            textFieldValor.setText(String.format("%.2f", ordemServico.getTotal()));
            
        }
    }

    @FXML
    private void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            ordemServico.setVeiculo(comboBoxVeiculos.getSelectionModel().getSelectedItem());
            ordemServico.setAgenda(datePickerData.getValue());
            ordemServico.setStatus((EStatus)choiceBoxStatus.getSelectionModel().getSelectedItem());
            ordemServico.setDesconto(Double.parseDouble(textFieldDesconto.getText()));
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleButtonCancelar() {
        dialogStage.close();
    }
    
    @FXML
    void handleTableViewMouseClicked(MouseEvent event) {
        ItemOS itemOS
                = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        if (itemOS == null) {
            contextMenuItemAtualizarObs.setDisable(true);
            contextMenuItemRemoverItem.setDisable(true);
        } else {
            contextMenuItemAtualizarObs.setDisable(false);
            contextMenuItemRemoverItem.setDisable(false);
        }

    }    

    @FXML
    private void handleContextMenuItemAtualizarObs() {
        ItemOS itemOS = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        int index = tableViewItensDeVenda.getSelectionModel().getSelectedIndex();
        
        String observacoesAtualizadas = inputDialog(itemOS.getObservacoes());
      
        itemOS.setObservacoes(observacoesAtualizadas);
        ordemServico.getItensOS().set(index, itemOS);
        tableViewItensDeVenda.refresh();
        textFieldValor.setText(String.format("%.2f", ordemServico.getTotal()));
    }
    
    private String inputDialog(String value) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrada de dados.");
        dialog.setHeaderText("Atualiza��o do campo observa��es do servi�o.");
        dialog.setContentText("Observa��es: ");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    @FXML
    private void handleContextMenuItemRemoverItem() {
        ItemOS itemDeVenda
                = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        int index = tableViewItensDeVenda.getSelectionModel().getSelectedIndex();
        ordemServico.getItensOS().remove(index);
        observableListItensOS = FXCollections.observableArrayList(ordemServico.getItensOS());
        tableViewItensDeVenda.setItems(observableListItensOS);

        textFieldValor.setText(String.format("%.2f", ordemServico.getTotal()));
    }

    //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (comboBoxVeiculos.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Veículo inv�lido!\n";
        }

        if (datePickerData.getValue() == null) {
            errorMessage += "Data inv�lida!\n";
        }

        if (observableListItensOS == null) {
            errorMessage += "Itens de Ordem de Servi�o inv�lidos!\n";
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            textFieldDesconto.setText(df.parse(textFieldDesconto.getText()).toString());
        } catch (ParseException ex) {
            errorMessage += "A taxa de desconto est� incorreta! Use \",\" como ponto decimal.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inv�lidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
