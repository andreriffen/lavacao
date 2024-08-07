package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador da caixa de diálogo para cadastro de clientes.
 * Este controlador é responsável por manipular a interação do usuário com 
 * os campos e botões da caixa de diálogo de cadastro de clientes.
 *
 * <br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
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
    
    /**
     * Inicializa o controlador da classe.
     * Este método é automaticamente chamado após o carregamento do arquivo FXML.
     *
     * @param url URL de localização do recurso FXML
     * @param rb ResourceBundle para localização dos recursos FXML
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Método para inicializações adicionais, se necessário.
    }       

    /**
     * Retorna o estado do botão Confirmar.
     *
     * @return true se o botão Confirmar foi clicado, false caso contrário.
     */
    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    /**
     * Define o estado do botão Confirmar.
     *
     * @param btConfirmarClicked true se o botão Confirmar foi clicado, false caso contrário.
     */
    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    /**
     * Retorna o estágio da caixa de diálogo.
     *
     * @return o estágio atual da caixa de diálogo.
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * Define o estágio da caixa de diálogo.
     *
     * @param dialogStage o novo estágio da caixa de diálogo.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Retorna o cliente sendo editado ou cadastrado.
     *
     * @return o objeto Cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que será editado ou cadastrado.
     * Também preenche os campos da caixa de diálogo com as informações do cliente.
     *
     * @param cliente o objeto Cliente a ser editado ou cadastrado.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.tfNome.setText(this.cliente.getNome());
        this.tfCpf.setText(this.cliente.getCpf());
        this.tfTelefone.setText(this.cliente.getTelefone());
        this.tfEndereco.setText(this.cliente.getEndereco());
        dpDataNascimento.setValue(this.cliente.getDataNascimento());
    }
    
    /**
     * Manipula o evento de clique no botão Confirmar.
     * Se os dados de entrada forem válidos, atualiza o objeto Cliente e fecha a caixa de diálogo.
     */
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
    
    /**
     * Manipula o evento de clique no botão Cancelar.
     * Fecha a caixa de diálogo sem salvar as alterações.
     */
    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }
    
    /**
     * Valida as entradas de dados nos campos da caixa de diálogo.
     * Verifica se os campos obrigatórios estão preenchidos corretamente.
     *
     * @return true se todos os dados de entrada forem válidos, false caso contrário.
     */
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) {
            errorMessage += "Nome inválido.\n";
        }
        
        if (this.tfCpf.getText() == null || this.tfCpf.getText().length() == 0) {
            errorMessage += "CPF inválido.\n";
        }
        
        if (this.tfTelefone.getText() == null || this.tfTelefone.getText().length() == 0) {
            errorMessage += "Telefone inválido.\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Exibe uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inválidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
