package br.edu.ifsc.fln.mainapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação que inicializa e carrega a interface gráfica
 * utilizando JavaFX.
 * 
 * <br> Refatorado por <b> andreriffen </b>
 * 
 * @author mpisc
 */
public class MainApp extends Application {
    
    /**
     * Inicializa a interface gráfica da aplicação.
     * 
     * @param primaryStage a janela principal da aplicação
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            // Carrega o layout principal da aplicação a partir do arquivo FXML
            root = FXMLLoader.load(getClass().getResource("../view/FXMLVBoxMainApp.fxml"));

            // Adiciona 50 quebras de linha
            String newLine = System.lineSeparator();
            String multipleNewLines = newLine.repeat(50);
            System.out.println(multipleNewLines);
            
        } catch (IOException ex) {
            // Exibe uma mensagem de erro caso não seja possível carregar o FXML
            System.out.println("Não foi possível carregar o formulário");
        }
        
        // Define a cena com o layout carregado, tamanho de 600x400 pixels
        Scene scene = new Scene(root, 600, 400);
        
        // Adiciona o ícone da aplicação
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../icon/IFSC_logo_vertical.png")));
        
        // Define o título da janela principal
        primaryStage.setTitle("Sistema de Vendas do IFSC Florianópolis");
        
        // Define a cena na janela principal
        primaryStage.setScene(scene);
        
        // Define que a janela não pode ser redimensionada
        primaryStage.setResizable(false);
        
        // Exibe a janela principal
        primaryStage.show();
    }

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args os argumentos da linha de comando
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
