package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAdmin {

    @FXML
    private Button btnAjouter;

    @FXML
    public void initialize() {
        btnAjouter.setOnAction(event -> loadGestionObjectif());
    }

    private void loadGestionObjectif() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnAjouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
