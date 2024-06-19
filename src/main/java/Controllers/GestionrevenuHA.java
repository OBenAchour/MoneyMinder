package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionrevenuHA {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button to_gestion_transactions;

    @FXML
    private Button to_cat_rev;

    @FXML
    private Button to_quote_rev;

    @FXML
    void initialize() {
        to_quote_rev.setOnAction(event->to_quote_rev());
        to_gestion_transactions.setOnAction(event->to_gestion_transactions());
    }

    private void to_gestion_transactions() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GererTransactionsHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_transactions.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to_quote_rev() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/quoterev.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_quote_rev.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
