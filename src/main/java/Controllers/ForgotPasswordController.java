package Controllers;

import Utils.JavaMailUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private void handleSendCodeAction() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your email");
            return;
        }

        // Generate and send a verification code
        String verificationCode = generateVerificationCode();
        boolean isEmailSent = JavaMailUtils.sendMail(email, "Password Reset Code", "Your verification code is: " + verificationCode);

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
