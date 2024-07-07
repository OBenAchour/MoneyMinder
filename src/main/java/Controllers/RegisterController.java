package Controllers;

import Models.User;
import Services.UserServices;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.scene.Parent;

public class RegisterController {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private DatePicker dateNaissField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

//    @FXML
//    public void handleRegisterAction(ActionEvent event) {
//        String nom = nomField.getText();
//        String prenom = prenomField.getText();
//        String email = emailField.getText();
//        String tel = telField.getText();
//        LocalDate dateNaiss = dateNaissField.getValue();
//        String password = passwordField.getText();
//        String confirmPassword = confirmPasswordField.getText();
//
//        if (!password.equals(confirmPassword)) {
//            showAlert("Error", "Passwords do not match.");
//            return;
//        }
//
//        if (dateNaiss != null) {
//            Date sqlDateNaiss = Date.valueOf(dateNaiss);
//
//            int telInt;
//            try {
//                telInt = Integer.parseInt(tel);
//            } catch (NumberFormatException e) {
//                showAlert("Error", "Telephone number must be an integer.");
//                return;
//            }
//
//            User user = new User(telInt, nom, prenom, password, email);
//            user.setDate_de_naiss(sqlDateNaiss);
//
//            UserServices userServices = new UserServices();
//            userServices.add(user);
//
//            showAlert("Success", "User registered successfully.");
//        } else {
//            showAlert("Error", "Please enter a valid date of birth.");
//        }
//    }


//    @FXML
//    public void handleBackAction(ActionEvent event) {
//        try {
//            // Load the welcome screen
//            Parent welcomeParent = FXMLLoader.load(getClass().getResource("/welcome.fxml"));
//            Scene welcomeScene = new Scene(welcomeParent);
//
//            // Get the current stage (window)
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            // Set the welcome scene on the stage
//            window.setScene(welcomeScene);
//            window.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleRegisterAction(javafx.event.ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String tel = telField.getText();
        LocalDate dateNaiss = dateNaissField.getValue();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        if (dateNaiss != null) {
            Date sqlDateNaiss = Date.valueOf(dateNaiss);

            int telInt;
            try {
                telInt = Integer.parseInt(tel);
            } catch (NumberFormatException e) {
                showAlert("Error", "Telephone number must be an integer.");
                return;
            }

            User user = new User(telInt, nom, prenom, password, email);
            user.setDate_de_naiss(sqlDateNaiss);

            UserServices userServices = new UserServices();
            userServices.add(user);

            showAlert("Success", "User registered successfully.");
        } else {
            showAlert("Error", "Please enter a valid date of birth.");
        }
    }

    public void handleBackAction(javafx.event.ActionEvent event) {
        try {
            // Load the welcome screen
            Parent welcomeParent = FXMLLoader.load(getClass().getResource("/welcome.fxml"));
            Scene welcomeScene = new Scene(welcomeParent);

            // Get the current stage (window)
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the welcome scene on the stage
            window.setScene(welcomeScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
