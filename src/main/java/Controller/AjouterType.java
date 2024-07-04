package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.Catobj;
import Services.CatobjService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterType {


    @FXML
    private TextField TF_cat;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button btnHomeAd;

    @FXML
    private Button btnV;

    @FXML
    private Button btnretour;

    @FXML
    void initialize() {
        btnretour.setOnAction(event -> RetourAjout());
        btnHomeAd.setOnAction(event -> retHomeAdmin());
        btnV.setOnAction(event -> validerAjout());
    }


    private void RetourAjout() {
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


    private void validerAjout() {
        String titre= TF_cat.getText();
        System.out.println(titre);
        Catobj cat = new Catobj();
        cat.setCatobj(titre);
        CatobjService catser = new CatobjService();
        catser.add(cat);
        navigateTo("/GestionObjectif.fxml");

    }

    private void navigateTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


