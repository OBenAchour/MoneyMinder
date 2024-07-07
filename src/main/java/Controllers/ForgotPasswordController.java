package Controllers;

import Services.UserServices;
import Utils.JavaMailUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.Parent;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    private UserServices userService = new UserServices(); // Create an instance of UserServices

    @FXML
    private void handleSendCodeAction() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your email");
            return;
        }

        // Check if the email is registered
        try {
            if (!userService.emailExists(email)) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "This email is not registered");
                return;
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while checking the email");
            e.printStackTrace();
            return;
        }

        // Generate and send a verification code
        String verificationCode = generateVerificationCode();
        String templatePath = "src/main/resources/passwordResetEmailTemplate.html";
        boolean isEmailSent = JavaMailUtils.sendMail(email, "Password Reset Code", templatePath, verificationCode);

        if (isEmailSent) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Verification code sent to your email");
            // Optionally save the verification code and email for verification
            // For now, just move to the next scene
            try {
                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/enterCode.fxml"));
                Parent root = loader.load();
                EnterCodeController enterCodeController = loader.getController();
                enterCodeController.setEmailAndCode(email, verificationCode);
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed", "Failed to send verification code. Please try again.");
        }
    }

    @FXML
    private void handleBackAction() {
        try {
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generateVerificationCode() {
        // Generate a 6-digit verification code
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
