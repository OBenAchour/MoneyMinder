package Controllers;

import Models.Sujet;
import Models.User;
import Servicesujet.SujetManager;
import Utils.Myconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;

public class Ajoutersujet {

    @FXML
    private AnchorPane IdText;

    @FXML
    private TextField sujettext;

    @FXML
    private TextField titretext;

    @FXML
    void Ajoutersujet(ActionEvent event) {
        String titre = titretext.getText();
        String contenu = sujettext.getText();

        // Vérifier si le titre ou le contenu contient des mots inappropriés
        if (InappropriateWordChecker.containsInappropriateWords(titre) || InappropriateWordChecker.containsInappropriateWords(contenu)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le titre ou le contenu contient des mots inappropriés. Veuillez les modifier.");
            alert.show();
            return;
        }

        Connection connection = Myconnection.getInstance().getCnx();

        User currentUser = new User(1); // Assurez-vous d'avoir un ID utilisateur valide
        Sujet sujet001 = new Sujet(0, titre, contenu, currentUser);
        SujetManager sujetmanager = new SujetManager(connection);

        sujetmanager.add(sujet001);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Sujet ajouté avec succès");
        alert.show();

        // Naviguer vers la liste des sujets
        showSujetList();
    }

    @FXML
    private void handleShowSubjectList(ActionEvent event) {
        showSujetList();
    }

    private void showSujetList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sujetListe.fxml"));
            Parent sujetListView = loader.load();
            IdText.getChildren().clear();
            IdText.getChildren().add(sujetListView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
