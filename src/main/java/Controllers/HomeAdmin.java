package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button to_Transactions;

    @FXML
    void Gerer_Transaction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        to_Transactions.setOnAction(event -> to_Transactions());
    }

    private void to_Transactions() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GererTransactionsHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_Transactions.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void response() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/responseGUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage errorCategoryStage = new Stage();
        errorCategoryStage.setScene(scene);
        errorCategoryStage.setTitle("MoneyMinder");
        errorCategoryStage.show();
    }
    @FXML
    void errorCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/createErrorCategory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage errorCategoryStage = new Stage();
        errorCategoryStage.setScene(scene);
        errorCategoryStage.setTitle("MoneyMinder");
        errorCategoryStage.show();
    }

}