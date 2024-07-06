package Controller;

import Models.Catobj;
import Models.Objectif;
import Services.ObjectifService;
import Utils.Myconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifierType {


    @FXML
    private TextField titreField;
    @FXML
    private Button btnretour;

    @FXML
    private Button btnHomeAd;

    @FXML
    private Button btnV;

    private Catobj cobj;


    @FXML
    private TextField typeTextField;

    //private Catobj catobjser = new ObjectifService();


    @FXML
    void initialize() {
        btnretour.setOnAction(event -> RetourModif());
        btnHomeAd.setOnAction(event -> retHomeAdmin());
        btnV.setOnAction(event -> ValiderModif());
    }


    private void RetourModif() {
        try {
            System.out.println("Chargement de l'interface modifier Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Retour avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void retHomeAdmin() {
        try {
            System.out.println("Chargement de l'interface retHomeAdmin");
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

    private void ValiderModif() {
        try {
            System.out.println("Chargement de l'interface modifier Objectif...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionObjectif.fxml"));
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
    public void setCatobj(Catobj catobjj) {
        this.cobj = catobjj;
        if (catobjj != null) {
            System.out.println("catégorie à modifier : " + catobjj);  // Debug
            titreField.setText(catobjj.getCatobj());
        } else {
            System.out.println("Erreur : carégorie est null.");  // Debug
        }
    }


}
