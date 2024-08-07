package br.edu.ifsc.fln.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador da interface principal da aplicação. 
 * Gerencia as ações dos itens de menu para cadastro de marcas, produtos, 
 * clientes, processos e relatórios.
 * 
 * <br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
public class FXMLVBoxMainAppController implements Initializable {

    @FXML
    private MenuItem menuItemCadastroMarca;
    @FXML
    private MenuItem menuItemCadastroModelo;
    @FXML
    private MenuItem menuItemCadastroCliente;
    @FXML
    private MenuItem menuItemProcessoEstoque;
    @FXML
    private MenuItem menuItemRelatorioEstoque;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuItemProcessoVenda;
    @FXML
    private MenuItem menuItemGraficoVendasPorMes;

    /**
     * Inicializa o controlador da classe. Este método é chamado automaticamente
     * após o carregamento do arquivo FXML associado.
     * 
     * @param url o local usado para resolver caminhos relativos para o objeto raiz ou nulo
     * @param rb o recurso para localizar o objeto raiz ou nulo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Método para inicialização (a ser implementado, se necessário)
    }  
    
    /**
     * Carrega a interface de cadastro de marcas na área principal da aplicação.
     * 
     * @throws IOException se ocorrer um erro ao carregar o arquivo FXML.
     */
    @FXML
    public void handleMenuItemCadastroMarca() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLAnchorPaneCadastroMarca.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    /**
     * Carrega a interface de cadastro de clientes na área principal da aplicação.
     * 
     * @throws IOException se ocorrer um erro ao carregar o arquivo FXML.
     */
    @FXML
    public void handleMenuItemCadastroCliente() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLAnchorPaneCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }    
    
    /**
     * Carrega a interface de cadastro de produtos na área principal da aplicação.
     * 
     * @throws IOException se ocorrer um erro ao carregar o arquivo FXML.
     */
    @FXML
    public void handleMenuItemCadastroModelo() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLAnchorPaneCadastroModelo.fxml"));
        anchorPane.getChildren().setAll(a);
    }     
}
