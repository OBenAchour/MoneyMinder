package Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Objectif;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FormulaireModif {

    @FXML
    private TextField titreField;

    @FXML
    private TextField montantGlobaleField;

    @FXML
    private TextField echeanceField;

    @FXML
    private TextField moisField;

    @FXML
    private TextField commentaireField;

    @FXML
    private Button btnretmodif;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnV;
    private Objectif objectif;

    @FXML
    void initialize() {
        btnretmodif.setOnAction(event -> RetourModif());
        btnHome.setOnAction(event -> retHome());
        btnV.setOnAction(event -> ValiderAjout());
    }



    private void RetourModif() {
        try {
            System.out.println("Chargement de l'interface modifier Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnretmodif.getScene().getWindow();
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

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
        titreField.setText(objectif.getTitre());
        montantGlobaleField.setText(objectif.getMontant_globale().toString());
        echeanceField.setText(objectif.getEcheance().toString());
        moisField.setText(Integer.toString(objectif.getMois()));
        commentaireField.setText(objectif.getCommentaire());
    }
}