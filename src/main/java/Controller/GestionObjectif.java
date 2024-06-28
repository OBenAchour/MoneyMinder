package Controller;

import Models.Catobj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Models.Objectif;
import Services.ObjectifService;

public class GestionObjectif {

    @FXML
    private TextField titreField;

    @FXML
    private TextField montantField;

    @FXML
    private TextField echeanceField;

    @FXML
    private TextField moisField;

    @FXML
    private TextField commentaireField;

    @FXML
    private Button btnAjouter;

    private ObjectifService objectifService;

    @FXML
    void initialize() {
        objectifService = new ObjectifService();

        btnAjouter.setOnAction(event -> {
            // Récupérer les données des champs
            String titre = titreField.getText();
            double montant_globale = Double.parseDouble(montantField.getText());
            double echeance = Double.parseDouble(echeanceField.getText());
            int mois = Integer.parseInt(moisField.getText());
            String commentaire = commentaireField.getText();

            // Créer un nouvel objectif
            Objectif nouvelObjectif = new Objectif(mois, "titre",  "commentaire",  montant_globale, echeance);

            // Ajouter l'objectif à la base de données
            objectifService.add(nouvelObjectif);

            // Effacer les champs après l'ajout (si nécessaire)
            clearFields();
        });
    }

    private void clearFields() {
        titreField.clear();
        montantField.clear();
        echeanceField.clear();
        moisField.clear();
        commentaireField.clear();
    }
}