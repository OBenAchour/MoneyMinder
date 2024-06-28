package Controller;

import Models.Objectif;
import Services.ObjectifService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionObjectif {

    @FXML
    private Button btnAjouter;

    private ObjectifService objectifService = new ObjectifService(); // Instance du service

    @FXML
    void initialize() {
        btnAjouter.setOnAction(event -> ouvrirFormulaireAjout());
    }

    private void ouvrirFormulaireAjout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterType.fxml"));
            Parent root = loader.load();
            AjouterType controller = loader.getController();
            controller.setObjectifService(objectifService); // Passer l'instance de ObjectifService au contrôleur AjouterType
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
