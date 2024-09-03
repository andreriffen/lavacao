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

import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaFisica;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Riffen
 */
public class FXMLAnchorPaneCadastroClienteDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private DatePicker dpDataCadastro;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private Group gbTipo;

    @FXML
    private RadioButton rbPessoaFisica;

    @FXML
    private RadioButton rbPessoaJuridica;

    @FXML
    private TextField tfCPF_CNPJ;

    @FXML
    private TextField tfCelular;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfIncricaoEstadual;

    @FXML
    private TextField tfNome;

    @FXML
    private ToggleGroup tgTipo;

    
    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Cliente cliente;
    
    /**
     * Initializes the controller class.
     */
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        
        this.tfNome.setText(this.cliente.getNome());
        this.tfCelular.setText(this.cliente.getCelular());
        this.tfEmail.setText(this.cliente.getEmail());
        this.tfEndereco.setText(this.cliente.getEndereco());
        this.dpDataCadastro.setValue(this.cliente.getDataCadastro());
        this.gbTipo.setDisable(true);
        if (cliente instanceof PessoaFisica) {
            this.dpDataNascimento.setValue(((PessoaFisica) this.cliente).getDataNascimento());
            rbPessoaFisica.setSelected(true);
            tfCPF_CNPJ.setText(((PessoaFisica) this.cliente).getCpf());
            tfIncricaoEstadual.setText("");
            tfIncricaoEstadual.setDisable(true);
        } else {
            this.dpDataNascimento.setValue(((PessoaJuridica) this.cliente).getDataNascimento());
            rbPessoaJuridica.setSelected(true);
            tfCPF_CNPJ.setText(((PessoaJuridica) this.cliente).getCnpj());
            tfIncricaoEstadual.setText(((PessoaJuridica) this.cliente).getInscricaoEstadual());
            tfIncricaoEstadual.setDisable(false);
        }
        this.tfNome.requestFocus();
    }
    

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            //condi��o para verificar se trata da atualiza��o ou de um novo cliente. 
            //Sendo novo, verifica qual � o tipo de cliente para fazer a inst�ncia apropriada. 
            if (this.cliente == null) {
                if (rbPessoaFisica.isSelected()) {
                    this.cliente = new PessoaFisica();
                } else {
                    this.cliente = new PessoaJuridica();
                }
            }
            cliente.setNome(tfNome.getText());
            cliente.setCelular(tfCelular.getText());
            cliente.setEmail(tfEmail.getText());
            cliente.setEndereco(tfEndereco.getText());
            cliente.setDataCadastro(dpDataCadastro.getValue());
            if (rbPessoaFisica.isSelected()) {
                ((PessoaFisica) cliente).setCpf(tfCPF_CNPJ.getText());
                ((PessoaFisica) cliente).setDataNascimento(dpDataNascimento.getValue());
            } else {
                ((PessoaJuridica) cliente).setCnpj(tfCPF_CNPJ.getText());
                ((PessoaJuridica) cliente).setDataNascimento(dpDataNascimento.getValue());
                ((PessoaJuridica) cliente).setInscricaoEstadual(tfIncricaoEstadual.getText());
            }
            btConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }
    
     @FXML
    public void handleRbPessoaFisica() {
        this.tfIncricaoEstadual.setText("");
        this.tfIncricaoEstadual.setDisable(true);
    }

    @FXML
    public void handleRbPessoaJuridica() {
        this.tfIncricaoEstadual.setText("");
        this.tfIncricaoEstadual.setDisable(false);
    }
    
    //m�todo para validar a entrada de dados
    private boolean validarEntradaDeDados() 
    {
        String errorMessage = "";
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) 
        {
            errorMessage += "Nome Inv�lido.\n";
        }
        
                
        if (this.tfCelular.getText() == null || this.tfCelular.getText().length() == 0) 
        {
            errorMessage += "Celular Inv�lido.\n";
        }
        
        if (this.tfEmail.getText() == null || this.tfEmail.getText().length() == 0) 
        {
            errorMessage += "Email Inv�lido.\n";
        }
        
        
        if (this.dpDataCadastro.getValue() == null || this.dpDataCadastro.getValue().toString().length() == 0) 
        {
            errorMessage += "Data de Cadastro Inv�lida.\n";
        }
        
        if (this.dpDataNascimento.getValue() == null || this.dpDataNascimento.getValue().toString().length() == 0)
        {
            errorMessage += "Data de Nascimento Inv�lida.\n";
        }
        
        if (rbPessoaFisica.isSelected()) 
        {
            if (this.tfCPF_CNPJ.getText() == null || this.tfCPF_CNPJ.getText().length() == 0)
            {
                errorMessage += "CPF Inv�lido.\n";
            }
        } 
        else 
        {
            if (this.tfCPF_CNPJ.getText() == null || this.tfCPF_CNPJ.getText().length() == 0) {
                errorMessage += "CNPJ Inv�lido.\n";
            }
            if (this.tfIncricaoEstadual.getText() == null || this.tfIncricaoEstadual.getText().length() == 0) {
                errorMessage += "Informe a Incri��o Estadual.\n";
            }
        }
        
        if (errorMessage.length() == 0) 
        {
            return true;
        } 
        else 
        {
            //exibindo uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inv�lidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
         
    }
}
