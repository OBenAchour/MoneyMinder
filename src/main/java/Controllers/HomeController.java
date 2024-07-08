package Controllers;

import Models.User;
import Utils.RestClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {
    private User u =null;
    private RestClient restClient = new RestClient();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFinancement;

    @FXML
    private Button to_depense;

    @FXML
    private Button to_revenu;
    public void setU(User u) {

        this.u = u;

    }



    @FXML
    void initialize(){
        System.out.println(u);
        to_depense.setOnAction(event ->to_depense());
        to_revenu.setOnAction(event ->to_revenu());
    }
    @FXML
    private void goToManageAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageAccount.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to_depense() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/DepenseH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_depense.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    void navigateToFinancement(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnFinancement.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/actions.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
