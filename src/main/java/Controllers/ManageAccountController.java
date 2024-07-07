package Controllers;

import Models.User;
import Services.UserServices;
import Utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ManageAccountController {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private TextField dateNaissField;

    private UserServices userServices;
    private User currentUser;

    @FXML
    public void initialize() {
        userServices = new UserServices();
        loadUserData();
    }

    private void loadUserData() {
        try {
            String userEmail = UserSession.getEmail();
            currentUser = userServices.getUserByEmail(userEmail);
            if (currentUser != null) {
                nomField.setText(currentUser.getNom());
                prenomField.setText(currentUser.getPrenom());
                emailField.setText(currentUser.getMail());
                telField.setText(String.valueOf(currentUser.getTel()));
                dateNaissField.setText(currentUser.getDate_de_naiss().toString());
            }
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors du chargement des données utilisateur : " + e.getMessage());
        }
    }

    @FXML
    private void handleSave() {
        currentUser.setNom(nomField.getText());
        currentUser.setPrenom(prenomField.getText());
        currentUser.setTel(Integer.parseInt(telField.getText()));
        currentUser.setDate_de_naiss(java.sql.Date.valueOf(dateNaissField.getText()));

        userServices.update(currentUser);
        showAlert("Succès", "Les informations utilisateur ont été mises à jour avec succès.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBackAction() {
        try {
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

