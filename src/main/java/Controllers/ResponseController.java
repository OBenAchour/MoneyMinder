
package Controllers;

import Models.Reclamation;
import Models.Response;
import Models.Status;
import Models.User;
import Services.ResponseService;
import Services.UserServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import Services.ReclamationService;

import java.sql.SQLException;
import java.util.List;

public class ResponseController {

    @FXML
    private TextField messageTextField;

    @FXML
    private ComboBox<Reclamation> reclamationTitleComboBox;


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
    private void handleAddResponse() throws SQLException {
        String message = messageTextField.getText();

        if (message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Message field cannot be empty. Please enter a valid message.");
            alert.showAndWait();
            return;
        }





        Reclamation reclamation = reclamationTitleComboBox.getValue();
        if (reclamation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please select a reclamation.");
            alert.showAndWait();
            return;
        }

        reclamation.setStatus(Status.SOLVED);
        reclamationService.modifier(reclamation.getId(), reclamation);
        UserServices userServices = new UserServices();
        User admin = userServices.getUserByEmail("fincompare@gmail.com");
        Response newResponse = new Response(reclamation, message, admin.getId());
        responseService.ajouter(newResponse);
        responses.add(newResponse);

        TreeItem<Response> newResponseItem = new TreeItem<>(newResponse);
        responseTableView.getRoot().getChildren().add(newResponseItem);

        clearFields();

        responseService.sendEmail(reclamation.getUserId(), newResponse.getMessage());
    }

    private void clearFields() {
        messageTextField.clear();

        reclamationTitleComboBox.setValue(null);
    }
}
