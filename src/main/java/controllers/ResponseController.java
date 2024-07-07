
package controllers;

import entities.Reclamation;
import entities.Response;
import entities.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import services.ReclamationService;
import services.ResponseService;

import java.util.List;

public class ResponseController {

    @FXML
    private TextField messageTextField;

    @FXML
    private ComboBox<Reclamation> reclamationTitleComboBox;

    @FXML
    private TextField adminIdTextField;

    @FXML
    private Button addResponseButton;

    @FXML
    private TreeTableView<Response> responseTableView;

    @FXML
    private TreeTableColumn<Response, Integer> idColumn;

    @FXML
    private TreeTableColumn<Response, String> messageColumn;

    @FXML
    private TreeTableColumn<Response, Integer> adminIdColumn;

    @FXML
    private TreeTableColumn<Response, Reclamation> reclamationColumn;

    private ResponseService responseService = new ResponseService();
    private ReclamationService reclamationService = new ReclamationService();
    private ObservableList<Response> responses = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setupComboBox();
        setupTableView();
        loadData();
    }

    private void setupComboBox() {
        List<Reclamation> reclamationList = reclamationService.afficher();
        ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamationList);
        reclamationTitleComboBox.setItems(observableReclamations);
    }

    private void setupTableView() {
        responseTableView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        messageColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("message"));
        adminIdColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("adminId"));
        reclamationColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("reclamation"));
        responseTableView.setRoot(new TreeItem<>());
        responseTableView.setShowRoot(false);
    }

    private void loadData() {
        responses.clear();
        responses.addAll(responseService.afficher());
        populateTreeTable(responses);
    }

    private void populateTreeTable(ObservableList<Response> responses) {
        TreeItem<Response> root = new TreeItem<>(null);
        for (Response response : responses) {
            TreeItem<Response> responseItem = new TreeItem<>(response);
            root.getChildren().add(responseItem);
        }
        responseTableView.setRoot(root);
    }

    @FXML
    private void handleAddResponse() {
        String message = messageTextField.getText();
        int adminId = Integer.parseInt(adminIdTextField.getText());
        System.out.println(adminId);
        Reclamation reclamation = reclamationTitleComboBox.getValue();
        reclamation.setStatus(Status.SOLVED);
        reclamationService.modifier(reclamation.getId(),reclamation);
        Response newResponse = new Response(reclamation,message, adminId);
        responseService.ajouter(newResponse);
        responses.add(newResponse);
        TreeItem<Response> newResponseItem = new TreeItem<>(newResponse);
        responseTableView.getRoot().getChildren().add(newResponseItem);
        clearFields();
    }

    private void clearFields() {
        messageTextField.clear();
        adminIdTextField.clear();
        reclamationTitleComboBox.getSelectionModel().clearSelection();
    }
}
