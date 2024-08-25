package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ClienteDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;

import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;

import java.time.format.DateTimeFormatter;

@SuppressWarnings("SpellCheckingInspection")
public class FXMLAnchorPaneCadastroClienteController implements Initializable {

    @FXML
    private Button btAlterar, btExcluir, btInserir;

    @FXML
    private Label lbClienteId, lbClienteCPF, lbClienteDataNascimento, lbClienteNome, lbClienteTelefone, lbClienteEndereco;

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCPF, tableColumnClienteNome;

    @FXML
    private TableView<Cliente> tableViewClientes;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }     

    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listaClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListClientes);
    }

    public void selecionarItemTableViewClientes(Cliente cliente) {
        if (cliente != null) {
            lbClienteId.setText(String.valueOf(cliente.getId())); 
            lbClienteNome.setText(cliente.getNome());
            lbClienteCPF.setText(cliente.getCpf());
            lbClienteTelefone.setText(cliente.getTelefone());
            lbClienteEndereco.setText(cliente.getEndereco());
            lbClienteDataNascimento.setText(String.valueOf(
                    cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } else {
            lbClienteId.setText(""); 
            lbClienteNome.setText("");
            lbClienteCPF.setText("");
            lbClienteTelefone.setText("");
            lbClienteEndereco.setText("");
            lbClienteDataNascimento.setText("");
        }
    }

    @FXML
    public void handleBtInserir() throws IOException {
        Cliente cliente = new Cliente();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(cliente);
        if (btConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
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
            alert.setContentText("Esta operação requer a seleção de um Cliente na tabela ao lado.");
            alert.show();
        }
    }

    @FXML
    public void handleBtExcluir() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção de um Cliente na tabela ao lado.");
            alert.show();
        }
    }


    private boolean showFXMLAnchorPaneCadastroClienteDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroClienteController.class.getResource("/view/FXMLAnchorPaneCadastroClienteDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        // Cria��o de um est�gio de di�logo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        // Enviando o objeto cliente para o controller
        FXMLAnchorPaneCadastroClienteDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        
        // Apresenta o di�logo e aguarda a confirma��o do usu�rio
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
}
