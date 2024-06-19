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

public class GestiondepenseHA {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button to_cat_dep;

    @FXML
    private Button to_gestion_transactions;

    @FXML
    private Button to_quote_dep;

    @FXML
    void initialize() {
        to_gestion_transactions.setOnAction(event ->to_gestion_transactions());
        to_cat_dep.setOnAction(event -> to_cat_dep());
        to_quote_dep.setOnAction(event -> to_quote_dep());
    }

    private void to_quote_dep() {
    }

    private void to_cat_dep() {
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

}
