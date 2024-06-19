package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Formulaire {

    @FXML
    private Button btnret;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnV;

    @FXML
    void initialize() {
        btnret.setOnAction(event -> RetourAjout());
        btnHome.setOnAction(event -> retHome());
        btnV.setOnAction(event -> ValiderAjout());
    }



        private void RetourAjout() {
            try {
                System.out.println("Chargement de l'interface modifier Objectif...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnret.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("Retour avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private void retHome() {
        try {
            System.out.println("Chargement de l'interface retHome...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Retour avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ValiderAjout() {
        try {
            System.out.println("Chargement de l'interface modifier Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Validation avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
