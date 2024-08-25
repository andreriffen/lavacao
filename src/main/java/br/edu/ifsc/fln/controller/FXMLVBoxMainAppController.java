package br.edu.ifsc.fln.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("SpellCheckingInspection")
public class FXMLVBoxMainAppController implements Initializable {

    @FXML
    private MenuItem menuItemCadastroCategoria;
    @FXML
    private MenuItem menuItemCadastroProduto;
    @FXML
    private MenuItem menuItemCadastroCliente;
    @FXML
    private MenuItem menuItemCadastroFornecedor;
    @FXML
    private MenuItem menuItemProcessoVenda;
    @FXML
    private MenuItem menuItemProcessoEstoque;
    @FXML
    private MenuItem menuItemGraficoVendaPorMes;
    @FXML
    private MenuItem menuItemRelatorioEstoque;
    @FXML 
    private MenuItem menuItemGraficosVendasPorMes;

    @FXML
    private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void handleMenuItemCadastroCategoria() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroCategoria.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastroCliente() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }    
    
    @FXML
    public void handleMenuItemCadastroMarca() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroMarca.fxml"));
        anchorPane.getChildren().setAll(a);
    }    

    @FXML
    public void handleMenuItemCadastroCor() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroCor.fxml"));
        anchorPane.getChildren().setAll(a);
    }     
    
    @FXML
    public void handleMenuItemProcessoEstoque() throws IOException {
        //TODO not implemented yet
    }     
    
    @FXML
    public void handleMenuItemProcessoVenda() throws IOException {
        //TODO not implemented yet
    }     
    
    @FXML
    public void handleMenuItemGraficosVendasPorMes() throws IOException {
        //TODO not implemented yet
    } 
    
    @FXML
    public void handleMenuItemRelatorioEstoqueProdutos() throws IOException {
        //TODO not implemented yet
    } 
    
}
