package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Marca;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings("SpellCheckingInspection")
public class FXMLAnchorPaneCadastroMarcaDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private TextField tfNome;
    
    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Marca marca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
        this.tfNome.setText(marca.getNome());
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            marca.setNome(tfNome.getText());

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

        if (tfNome.getText() == null || tfNome.getText().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Exibe uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro na validação dos dados");
            alert.setHeaderText("Por favor, corrija os campos inválidos");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
