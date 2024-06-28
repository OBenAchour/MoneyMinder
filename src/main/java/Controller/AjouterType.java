package Controller;

import Models.Objectif;
import Services.ObjectifService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterType {

    @FXML
    private TextField txtTitre;

    @FXML
    private TextField txtMontant;

    @FXML
    private Button btnAjouterObjectif;

    private ObjectifService objectifService;

    public void setObjectifService(ObjectifService objectifService) {
        this.objectifService = objectifService;
    }

    @FXML
    private void initialize() {
        btnAjouterObjectif.setOnAction(event -> ajouterObjectif());
    }

    @FXML
    private void ajouterObjectif() {
        String titre = txtTitre.getText();
        double montant = Double.parseDouble(txtMontant.getText());

        // Exemple de création d'un nouvel objectif
        Objectif nouvelObjectif = new Objectif();
        nouvelObjectif.setTitre(titre);
        nouvelObjectif.setMontant_globale(montant);

        // Appel à ObjectifService pour ajouter l'objectif
        objectifService.add(nouvelObjectif);

        // Fermer la fenêtre de dialogue après l'ajout
        Stage stage = (Stage) txtTitre.getScene().getWindow();
        stage.close();
    }
}
