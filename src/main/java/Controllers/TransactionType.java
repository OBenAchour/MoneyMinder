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

public class TransactionType {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_transaction_type;

    @FXML
    private Button delete_transaction_type;

    @FXML
    private Button to_gestion_transaction;

    @FXML
    private Button update_transaction_type;

    @FXML
    void initialize() {
     to_gestion_transaction.setOnAction(event->to_gestion_transaction());
    }

    private void to_gestion_transaction() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GererTransactionsHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_transaction.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
