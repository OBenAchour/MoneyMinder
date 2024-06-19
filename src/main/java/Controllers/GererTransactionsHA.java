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

public class GererTransactionsHA {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button to_frequence;

    @FXML
    private Button to_gestion_depense;

    @FXML
    private Button to_gestion_revenu;

    @FXML
    private Button to_home_admin;
    {
    }

    @FXML
    private Button to_transaction_type;

    @FXML
    void initialize() {
        to_home_admin.setOnAction(event->to_home_admin());
        to_transaction_type.setOnAction(event->to_transaction_type());
        to_frequence.setOnAction(event->to_frequence());
        to_gestion_revenu.setOnAction(event->to_gestion_revenu());
    }

    private void to_gestion_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GestionrevenuHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_frequence.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to_frequence() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Frequence.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_frequence.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to_transaction_type() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/TransactionTypeHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_transaction_type.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to_home_admin() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/HomeAdmin.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_home_admin.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
