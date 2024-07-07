package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SoldeController {

    @FXML
    private Label soldeLabel;

    // Méthode pour initialiser le solde affiché dans solde.fxml
    public void setSolde(double solde) {
        soldeLabel.setText("Solde: $" + solde);
    }
}
