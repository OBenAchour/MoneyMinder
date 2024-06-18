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

public class Homee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnret;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        btnajouter.setOnAction(event -> loadFormulaire());
        btnmodifier.setOnAction(event -> loadFormulaireModif());
//        btnret.setOnAction(event -> RetourAjout ());
    }

    private void loadFormulaire() {
        try {
            System.out.println("Chargement de l'interface Ajouter Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Formulaire.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface Ajouter Objectif chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadFormulaireModif() {
        try {
            System.out.println("Chargement de l'interface modifier Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaireModif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface modifier Objectif chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void RetourAjout() {
//        try {
//            System.out.println("Chargement de l'interface modifier Objectif...");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) btnret.getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Retour avec succès !");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


