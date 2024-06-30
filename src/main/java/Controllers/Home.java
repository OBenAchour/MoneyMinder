package Controllers;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Home{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button to_depense;

    @FXML
    private Button to_revenu;


    @FXML
    void initialize() {
        to_depense.setOnAction(event ->to_depense());
        to_revenu.setOnAction(event ->to_revenu());

    }

    private void to_depense() {
    }

    private void to_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/RevenuH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_revenu.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}