package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Modelo;
import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.domain.Motor;
import br.edu.ifsc.fln.model.domain.ECategoria;
import java.net.URL;
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

@SuppressWarnings("SpellCheckingInspection")
public class FXMLAnchorPaneCadastroModeloDialogController implements Initializable {

    @FXML
    private TextField tfDescricao;
    @FXML
    private ComboBox<Marca> cbMarca;
    @FXML
    private ComboBox<Motor> cbMotor;
    @FXML
    private ComboBox<ECategoria> cbCategoria;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;

    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Modelo modelo;

    private ObservableList<Marca> observableListMarcas;
    private ObservableList<Motor> observableListMotores;
    private ObservableList<ECategoria> observableListCategorias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Preencher os ComboBox com as op��es necess�rias (exemplo)
        observableListMarcas = FXCollections.observableArrayList(); // Adicionar a lista de marcas
        cbMarca.setItems(observableListMarcas);

        observableListMotores = FXCollections.observableArrayList(); // Adicionar a lista de motores
        cbMotor.setItems(observableListMotores);

        observableListCategorias = FXCollections.observableArrayList(ECategoria.values());
        cbCategoria.setItems(observableListCategorias);
    }       

    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
        this.tfDescricao.setText(this.modelo.getDescricao());
        this.cbMarca.setValue(this.modelo.getMarca());
        this.cbMotor.setValue(this.modelo.getMotor());
        this.cbCategoria.setValue(this.modelo.getCategoria());
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            modelo.setDescricao(tfDescricao.getText());
            modelo.setMarca(cbMarca.getValue());
            modelo.setMotor(cbMotor.getValue());
            modelo.setCategoria(cbCategoria.getValue());

            btConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }

    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (this.tfDescricao.getText() == null || this.tfDescricao.getText().length() == 0) {
            errorMessage += "Descri��o inv�lida.\n";
        }
        if (this.cbMarca.getValue() == null) {
            errorMessage += "Marca inv�lida.\n";
        }
        if (this.cbMotor.getValue() == null) {
            errorMessage += "Motor inv�lido.\n";
        }
        if (this.cbCategoria.getValue() == null) {
            errorMessage += "Categoria inv�lida.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inv�lidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
