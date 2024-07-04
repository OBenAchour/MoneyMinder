package Controller;

import Models.Objectif;
import Services.ObjectifService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FormulaireModif {

    @FXML
    private TextField titreField;

    @FXML
    private TextField montantGlobaleField;

    //@FXML
    //private TextField echeanceField;

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

    private ObjectifService objectifService = new ObjectifService();

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
        if (objectif == null) {
            System.out.println("Erreur : aucun objectif à modifier.");
            return;
        }

        objectif.setTitre(titreField.getText());
        objectif.setMontant_globale(Double.parseDouble(montantGlobaleField.getText()));

        objectif.setMois(Integer.parseInt(moisField.getText()));
        objectif.setCommentaire(commentaireField.getText());

        objectifService.update(objectif);

        System.out.println("Objectif mis à jour avec succès !");

        // Fermer le formulaire de modification
        Stage stage = (Stage) btnV.getScene().getWindow();
        stage.close();

        // Vous pouvez aussi ajouter une méthode pour recharger les données de la table dans l'interface principale
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
        if (objectif != null) {
            System.out.println("Objectif à modifier : " + objectif);  // Debug
            titreField.setText(objectif.getTitre());
            montantGlobaleField.setText(objectif.getMontant_globale().toString());
            //echeanceField.setText(objectif.getEcheance().toString()); // Assuming echeance est un double
            moisField.setText(Integer.toString(objectif.getMois()));
            commentaireField.setText(objectif.getCommentaire());
        } else {
            System.out.println("Erreur : Objectif est null.");  // Debug
        }
    }
}
