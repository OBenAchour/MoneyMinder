package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Models.Catobj;
import Models.Catrev;
import Models.Objectif;
import Services.CatobjService;
import Services.ObjectifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Formulaire {

    @FXML
    private Button btnret;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnV;

    @FXML
    private TextField titreTextField;

    @FXML
    private TextField budgetTextField;

    @FXML
    private TextField moisTextField;

    @FXML
    private TextField commentaireTextField;

    @FXML
    private ComboBox<String> typecat;

    @FXML
    void initialize() {
        btnret.setOnAction(event -> retourAjouterObjectif());
        btnHome.setOnAction(event -> retourHome());
        btnV.setOnAction(event -> validerAjout());
        typecat.setItems(CRdata);

    }

    private void retourAjouterObjectif() {
        navigateTo("/AjouterObjectif.fxml");
    }

    private void retourHome() {
        navigateTo("/Home.fxml");
    }

    private void validerAjout() {
        String titre = titreTextField.getText();
        Double montant_globale = Double.parseDouble(budgetTextField.getText());
        Integer mois = Integer.parseInt(moisTextField.getText());
        String commentaire = commentaireTextField.getText();

        String cat = typecat.getValue();
        List<Catobj> catobjs= cr.getbyfilter("catobj",cat);

        Objectif obj = new Objectif( mois, titre, commentaire, montant_globale, catobjs.get(0));
        ObjectifService objser = new ObjectifService();
        objser.add(obj);

        navigateTo("/AjouterObjectif.fxml");

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

    CatobjService cr = new CatobjService();
    List<Catobj> Categories = cr.getAll();
    public ObservableList<String> CRdata = FXCollections.observableArrayList(Categories.stream().map(CR->CR.getCatobj()).toList());


}
