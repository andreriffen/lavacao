package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

@SuppressWarnings("SpellCheckingInspection")
public class FXMLAnchorPaneCadastroClienteDialogController implements Initializable {

    @FXML
    private Button btCancelar, btConfirmar;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfCpf, tfEndereco, tfNome, tfTelefone;
    
    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // M�todo para inicializa��es adicionais, se necess�rio.
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.tfNome.setText(this.cliente.getNome());
        this.tfCpf.setText(this.cliente.getCpf());
        this.tfTelefone.setText(this.cliente.getTelefone());
        this.tfEndereco.setText(this.cliente.getEndereco());
        dpDataNascimento.setValue(this.cliente.getDataNascimento());
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            cliente.setNome(tfNome.getText());
            cliente.setCpf(tfCpf.getText());
            cliente.setTelefone(tfTelefone.getText());
            cliente.setEndereco(tfEndereco.getText());
            cliente.setDataNascimento(dpDataNascimento.getValue());

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
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) {
            errorMessage += "Nome inv�lido.\n";
        }
        
        if (this.tfCpf.getText() == null || this.tfCpf.getText().length() == 0) {
            errorMessage += "CPF inv�lido.\n";
        }
        
        if (this.tfTelefone.getText() == null || this.tfTelefone.getText().length() == 0) {
            errorMessage += "Telefone inv�lido.\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Exibe uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inv�lidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
