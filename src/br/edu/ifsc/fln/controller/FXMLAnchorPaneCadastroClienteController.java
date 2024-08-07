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


/**
 * Controlador para a interface de cadastro de clientes.
 * 
 * Este controlador gerencia as opera��es de cadastro, altera��o e exclus�o
 * de clientes, al�m de carregar e exibir os dados na interface gr�fica.
 * 
 * <br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
public class FXMLAnchorPaneCadastroClienteController implements Initializable {

    // Declara��es de elementos da interface gr�fica da tela de cadastro de clientes
    
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

    /**
     * Inicializa o controlador da interface gr�fica.
     * 
     * Este m�todo � automaticamente chamado ap�s o carregamento do arquivo FXML.
     * Configura a conex�o com o banco de dados e carrega os clientes na tabela.
     * 
     * @param url O caminho do recurso utilizado para localizar o arquivo FXML.
     * @param rb O conjunto de recursos utilizados para localizar o arquivo FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }     
    
    /**
     * Carrega os dados dos clientes na TableView.
     * 
     * Este m�todo consulta o banco de dados para obter a lista de clientes
     * e a exibe na TableView.
     */
    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listaClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
    /**
     * Exibe os detalhes do cliente selecionado na TableView.
     * 
     * Quando um cliente � selecionado na TableView, seus detalhes s�o exibidos
     * nos labels correspondentes.
     * 
     * @param cliente O cliente selecionado na TableView.
     */
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
    
    /**
     * Trata o evento de clique no bot�o Inserir.
     * 
     * Abre o di�logo de cadastro de cliente para inserir um novo cliente. Ap�s
     * a confirma��o, o cliente � inserido no banco de dados e a TableView � atualizada.
     * 
     * @throws IOException Se ocorrer um erro ao carregar o di�logo.
     */
    @FXML
    public void handleBtInserir() throws IOException {
        Cliente cliente = new Cliente();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(cliente);
        if (btConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        } 
    }
    
    /**
     * Trata o evento de clique no bot�o Alterar.
     * 
     * Abre o di�logo de cadastro de cliente para alterar os dados do cliente selecionado.
     * Ap�s a confirma��o, os dados do cliente s�o atualizados no banco de dados e a
     * TableView � atualizada.
     * 
     * @throws IOException Se ocorrer um erro ao carregar o di�logo.
     */
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
            alert.setContentText("Esta opera��o requer a sele��o de um Cliente na tabela ao lado.");
            alert.show();
        }
    }
    
    /**
     * Trata o evento de clique no bot�o Excluir.
     * 
     * Remove o cliente selecionado do banco de dados e atualiza a TableView.
     * Se nenhum cliente estiver selecionado, exibe um alerta de erro.
     * 
     * @throws IOException Se ocorrer um erro ao carregar o di�logo.
     */
    @FXML
    public void handleBtExcluir() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o de um Cliente na tabela ao lado.");
            alert.show();
        }
    }

    /**
     * Exibe o di�logo de cadastro de cliente.
     * 
     * Este m�todo carrega o di�logo de cadastro de cliente a partir de um arquivo FXML,
     * configura o est�gio do di�logo e passa o cliente selecionado para o controlador.
     * 
     * @param cliente O cliente a ser cadastrado ou alterado.
     * @return true se o bot�o Confirmar for clicado, false caso contr�rio.
     * @throws IOException Se ocorrer um erro ao carregar o di�logo.
     */
    private boolean showFXMLAnchorPaneCadastroClienteDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroClienteController.class.getResource("../view/FXMLAnchorPaneCadastroClienteDialog.fxml"));
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
