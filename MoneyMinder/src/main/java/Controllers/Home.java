package Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;

public class Home extends Application {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFinancement;

    @FXML
    void initialize() {
        assert btnFinancement != null : "fx:id=\"btnFinancement\" was not injected: check your FXML file 'Home.fxml'.";

    }


    @FXML
    void navigateToFinancement(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnFinancement.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/actions.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
