package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    @FXML
    private Button btnObj;



    @FXML
    public void initialize() {
        btnObj.setOnAction(event -> loadHomee());

    }


    private void loadHomee() {
        try {
            System.out.println("Chargement de l'interface Homee");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnObj.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Interface Homee chargée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


