package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;

public class EnterCodeController {

    @FXML
    private TextField codeField;

    private String email;
    private String verificationCode;

    public void setEmailAndCode(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

    @FXML
    private void handleVerifyCodeAction() {
        String enteredCode = codeField.getText();

        if (enteredCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter the verification code");
            return;
        }

        if (enteredCode.equals(verificationCode)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Verification code is correct");

            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resetPassword.fxml"));
                Parent root = loader.load();
                ResetPasswordController resetPasswordController = loader.getController();
                resetPasswordController.setEmail(email);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // Close the current window
                Stage currentStage = (Stage) codeField.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed", "Incorrect verification code. Please try again.");
        }
    }

    @FXML
    private void handleBackAction() {
        try {
            Stage stage = (Stage) codeField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/forgotPassword.fxml"));
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
}
