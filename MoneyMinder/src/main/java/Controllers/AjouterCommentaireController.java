package Controllers;

import Models.Commentaire;
import Models.Sujet;
import Servicesujet.CmtManager;
import Utils.Myconnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;

public class AjouterCommentaireController {

    @FXML
    private TextArea commentaireTextArea;

    private Sujet sujet;

    private Connection connection;
    private CmtManager cmtManager;

    private SujetListeController parentController;

    public AjouterCommentaireController() {
        connection = Myconnection.getInstance().getCnx();
        cmtManager = new CmtManager(connection);
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public void setParentController(SujetListeController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleAddComment() {
        String contenuCommentaire = commentaireTextArea.getText();

        if (contenuCommentaire.isEmpty()) {
            showAlert("Le commentaire ne peut pas être vide.");
            return;
        }

        // Vérifier si le commentaire contient des mots inappropriés
        if (InappropriateWordChecker.containsInappropriateWords(contenuCommentaire)) {
            showAlert("Le commentaire contient des mots inappropriés. Veuillez les modifier.");
            return;
        }

        Commentaire commentaire = new Commentaire(0, contenuCommentaire, sujet);
        cmtManager.add(commentaire);

        showAlert("Commentaire ajouté avec succès.");

        // Fermer la fenêtre après l'ajout du commentaire
        Stage stage = (Stage) commentaireTextArea.getScene().getWindow();
        stage.close();

        // Recharger la liste des sujets
        if (parentController != null) {
            parentController.loadSujetList();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
