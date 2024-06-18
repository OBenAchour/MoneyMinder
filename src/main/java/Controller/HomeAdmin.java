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
    private Button btnGesObj;



    @FXML
    public void initialize() {
        btnGesObj.setOnAction(event -> loadHomee());

    }


    private void loadHomee() {
        try {
            System.out.println("Chargement de l'interface Homee");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnGesObj.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface Ajouter Objectif chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
