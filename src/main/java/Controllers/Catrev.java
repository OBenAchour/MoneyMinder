package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Quoterev;
import Services.GestionTransactionsServices.CatrevService;
import Services.GestionTransactionsServices.FrequenceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Catrev {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Models.Catrev, String> Catrev;

    @FXML
    private TableColumn<Models.Catrev,Integer> ID;

    @FXML
    private Button Refresh;

    @FXML
    private TableView<Models.Catrev> Table;

    @FXML
    private Button add_catrev;

    @FXML
    private Button delete_catrev;

    @FXML
    private Button to_gestion_revenu;

    @FXML
    private Button update_catrev;

    public ObservableList<Models.Catrev> CRdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
       to_gestion_revenu.setOnAction(event ->to_gestion_revenu());
       Refresh.setOnAction(event -> view_Categorie_Revenu());
    }

    private void view_Categorie_Revenu() {
        CatrevService cr = new CatrevService();
        List<Models.Catrev> Categories = cr.getAll();
        CRdata.clear();
        CRdata.addAll(Categories);
        ID.setCellValueFactory(new PropertyValueFactory<Models.Catrev, Integer>("id"));
        Catrev.setCellValueFactory(new PropertyValueFactory<Models.Catrev, String>("type"));
        Table.setItems(CRdata);
    }

    private void to_gestion_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GestionrevenuHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_revenu.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
