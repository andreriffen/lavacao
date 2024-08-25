package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ModeloDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Modelo;

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

@SuppressWarnings("SpellCheckingInspection")
public class FXMLAnchorPaneCadastroModeloController implements Initializable {

    @FXML
    private TableView<Modelo> tableViewModelo;
    @FXML
    private TableColumn<Modelo, String> tableColumnModeloDescricao;
    @FXML
    private Label lbModeloId, lbModeloDescricao, lbMarcaId, lbMotorId, lbCategoria;
    @FXML
    private Button btInserir, btAlterar, btExcluir;

    private List<Modelo> listaModelos;
    private ObservableList<Modelo> observableListModelos;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    
    private final ModeloDAO modeloDAO = new ModeloDAO();
    
    //private final MarcaDAO marcaDAO = new MarcaDAO();
    //private final MotorDAO motorDAO = new MotorDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //modeloDAO.setConnection(connection);
        //marcaDAO.setConnection(connection);
        //motorDAO.setConnection(connection);
        carregarTableViewModelo();
        
        tableViewModelo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewModelo(newValue));
    }     

    public void carregarTableViewModelo() {
        tableColumnModeloDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        listaModelos = modeloDAO.listar();
        
        observableListModelos = FXCollections.observableArrayList(listaModelos);
        tableViewModelo.setItems(observableListModelos);
    }
    
    public void selecionarItemTableViewModelo(Modelo modelo) {
        if (modelo != null) {
            lbModeloId.setText(String.valueOf(modelo.getId())); 
            lbModeloDescricao.setText(modelo.getDescricao());
            lbMarcaId.setText(String.valueOf(modelo.getMarca().getId()));
            
            //lbCategoria.setText(modelo.getCategoria().toString());
            //lbMotorId.setText(String.valueOf(modelo.getMotor().getId()));
            
        } else {
            lbModeloId.setText(""); 
            lbModeloDescricao.setText("");
            
            //Ser�??
            //lbMarcaId.setText("");
            //lbMotorId.setText("");
            //lbCategoria.setText("");
        }
    }

    @FXML
    public void handleBtInserir() throws IOException {
        Modelo modelo = new Modelo();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroModeloDialog(modelo);
        if (btConfirmarClicked) {
            modeloDAO.inserir(modelo);
            carregarTableViewModelo();
        } 
    }

    @FXML 
    public void handleBtAlterar() throws IOException {
        Modelo modelo = tableViewModelo.getSelectionModel().getSelectedItem();
        if (modelo != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroModeloDialog(modelo);
            if (btConfirmarClicked) {
                modeloDAO.alterar(modelo);
                carregarTableViewModelo();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o de um Modelo na tabela ao lado.");
            alert.show();
        }
    }

    @FXML
    public void handleBtExcluir() throws IOException {
        Modelo modelo = tableViewModelo.getSelectionModel().getSelectedItem();
        if (modelo != null) {
            modeloDAO.remover(modelo);
            carregarTableViewModelo();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta opera��o requer a sele��o de um Modelo na tabela ao lado.");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroModeloDialog(Modelo modelo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroModeloController.class.getResource("../view/FXMLAnchorPaneCadastroModeloDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Modelo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLAnchorPaneCadastroModeloDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setModelo(modelo);
        
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
}
