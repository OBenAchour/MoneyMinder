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

public class GestionObjectif {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnajoutertype;
    @FXML
    private Button btnmodifierType;

    @FXML
    private Button btnHomeAd;


    @FXML
    private URL location;

    @FXML
    void initialize() {
        btnajoutertype.setOnAction(event -> loadAjouterType());
        btnmodifierType.setOnAction(event -> loadFormulaireModif());
        btnHomeAd.setOnAction(event -> loadretHomeAdmin());
    }


    private void loadAjouterType() {
        try {
            System.out.println("Chargement de l'interface modifier Type...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnajoutertype.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface Ajouter Type chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadFormulaireModif() {
        try {
            System.out.println("Chargement de l'interface modifier Type...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnmodifierType.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface modifier Type chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void loadretHomeAdmin() {
            try {
                System.out.println("Chargement de l'interface retHomeAdmin...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeAdmin.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnHomeAd.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("Retour avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


