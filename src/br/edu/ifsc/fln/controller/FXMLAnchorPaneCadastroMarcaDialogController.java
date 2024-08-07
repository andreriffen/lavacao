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

/**
 * Controlador responsável pelo gerenciamento da interface de diálogo para 
 * cadastro e edição de marcas.
 *
 * <br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
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
    
    /**
     * Inicializa o controlador. Este método é chamado automaticamente após o 
     * carregamento do arquivo FXML.
     * 
     * @param url Caminho para o local do recurso.
     * @param rb Recursos de localização específicos para o controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       

    /**
     * Retorna o estado do botão Confirmar.
     * 
     * @return true se o botão Confirmar foi clicado, caso contrário false.
     */
    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    /**
     * Define o estado do botão Confirmar.
     * 
     * @param btConfirmarClicked Novo estado do botão Confirmar.
     */
    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    /**
     * Retorna o estágio do diálogo.
     * 
     * @return Estágio do diálogo.
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * Define o estágio do diálogo.
     * 
     * @param dialogStage Novo estágio do diálogo.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Retorna a marca que está sendo editada ou cadastrada.
     * 
     * @return Marca atual.
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * Define a marca que será editada ou cadastrada e atualiza os campos da 
     * interface com os dados da marca.
     * 
     * @param marca Nova marca.
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
        this.tfNome.setText(marca.getNome());
    }
    
    /**
     * Manipula o evento do botão Confirmar. Valida os dados e, se corretos, 
     * salva as alterações e fecha o diálogo.
     */
    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            marca.setNome(tfNome.getText());

            btConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Manipula o evento do botão Cancelar. Fecha o diálogo sem salvar as alterações.
     */
    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }
    
    /**
     * Valida a entrada de dados nos campos de texto. Exibe um alerta se algum campo 
     * estiver inválido.
     * 
     * @return true se os dados são válidos, caso contrário false.
     */
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
