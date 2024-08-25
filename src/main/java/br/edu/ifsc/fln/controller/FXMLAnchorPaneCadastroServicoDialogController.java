package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.dao.CategoriaDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Categoria;
import br.edu.ifsc.fln.model.domain.Servico;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class FXMLAnchorPaneCadastroServicoDialogController {

    @FXML
    public Button btConfirmar;

    @FXML
    public Button btCancelar;

    @FXML
    public TextField tfDescricao;

    @FXML
    public TextField tfValor;

    @FXML
    public Spinner<Integer> spPontos;

    @FXML
    public ComboBox<Categoria> cbCategoria;

    private Stage dialogStage;
    private Servico servico;
    private boolean btConfirmarClicked = false;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        if (this.servico != null) {
            tfDescricao.setText(this.servico.getDescricao());
            tfValor.setText(String.valueOf(this.servico.getValor()));
            spPontos.getValueFactory().setValue(this.servico.getPontos());

            // Populate ComboBox with categories
            List<Categoria> categorias = carregarCategorias();
            cbCategoria.getItems().addAll(categorias);
            cbCategoria.setValue(this.servico.getCategoria()); // Assume Servico has a Categoria object

        }
    }

    @FXML
    private void handleBtConfirmar() {
        if (isInputValid()) {
            servico.setDescricao(tfDescricao.getText());
            servico.setValor(Double.parseDouble(tfValor.getText()));
            servico.setCategoria(cbCategoria.getValue());

            btConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleBtCancelar() {
        dialogStage.close();
    }

    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (tfDescricao.getText() == null || tfDescricao.getText().length() == 0) {
            errorMessage += "Descrição inválida!\n";
        }
        if (tfValor.getText() == null || tfValor.getText().length() == 0) {
            errorMessage += "Valor inválido!\n";
        } else {
            try {
                Double.parseDouble(tfValor.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Valor inválido (deve ser um número)!\n";
            }
        }
        if (spPontos.getValue() == null) {
            errorMessage += "Pontos inválidos!\n";
        }
        if (cbCategoria.getValue() == null) {
            errorMessage += "Categoria não selecionada!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            showAlert(AlertType.ERROR, "Campos inválidos", "Por favor, corrija os campos inválidos", errorMessage);
            return false;
        }
    }

    private List<Categoria> carregarCategorias() {
        try {
            return categoriaDAO.listar();
        } catch (DAOException ex) {
            showAlert(AlertType.ERROR, "Erro ao carregar categorias", "Não foi possível carregar as categorias", ex.getMessage());
            return List.of(); // Return empty list on error
        }
    }

    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        spPontos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    }
}
