package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;

import Services.UserServices;
import javafx.scene.Parent;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    private UserServices userServices;

    public LoginController() {
        userServices = new UserServices();
    }

    @FXML
    private void handleLoginAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            int userId = userServices.login(email, password);
            if (userId != -1) {
                // Login successful, navigate to Home.fxml
                Stage stage = (Stage) emailField.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/Home.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Login failed, show error message
                errorMessageLabel.setText("Invalid email or password.");
                errorMessageLabel.setVisible(true);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            errorMessageLabel.setText("An error occurred. Please try again.");
            errorMessageLabel.setVisible(true);
        }
    }

    @FXML
    private void handleForgotPasswordAction() {
        try {
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/forgot_password.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("An error occurred. Please try again.");
            errorMessageLabel.setVisible(true);
        }
    }


    @FXML
    private void handleBackAction() {
        try {
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Welcome.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
