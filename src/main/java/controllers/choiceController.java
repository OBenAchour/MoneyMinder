package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class choiceController {
    @FXML
    private Button errorCategoryButton;

    @FXML
    private Button reclamationButton;

    @FXML
    private Button responseButton;

    @FXML
    void errorCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/createErrorCategory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage errorCategoryStage = new Stage();
        errorCategoryStage.setScene(scene);
        errorCategoryStage.setTitle("MoneyMinder");
        errorCategoryStage.show();
    }

    @FXML
    void reclamation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/reclamationGUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage errorCategoryStage = new Stage();
        errorCategoryStage.setScene(scene);
        errorCategoryStage.setTitle("MoneyMinder");
        errorCategoryStage.show();
    }

    @FXML
    void response() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/responseGUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage errorCategoryStage = new Stage();
        errorCategoryStage.setScene(scene);
        errorCategoryStage.setTitle("MoneyMinder");
        errorCategoryStage.show();
    }

}
