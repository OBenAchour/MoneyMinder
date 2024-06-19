package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Panier {
    @FXML
    private Button btnHome;
    @FXML
    public void initialize() {
        btnHome.setOnAction(event -> loadAssets());
    }
        private void loadAssets() {
            try {
                System.out.println("Chargement de l'interface Assets");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnHome.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("Interface Ajouter Objectif chargée avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

