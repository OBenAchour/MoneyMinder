package Controller;

import Models.Objectif;
import Services.ObjectifService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionObjectif {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    private ObjectifService objectifService = new ObjectifService(); // Instance du service

    @FXML
    void initialize() {
        btnAjouter.setOnAction(event -> loadAjouterType());
        btnModifier.setOnAction(event -> loadModifierType());
    }


    private void loadAjouterType() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnAjouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadModifierType() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
