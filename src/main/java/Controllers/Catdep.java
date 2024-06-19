package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.CatDep;
import Models.Catrev;
import Services.GestionTransactionsServices.CatdepService;
import Services.GestionTransactionsServices.CatrevService;
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

public class Catdep {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CatDep, String> Catdep;

    @FXML
    private TableColumn<CatDep, Integer> ID;

    @FXML
    private Button Refresh;

    @FXML
    private TableView<CatDep> Table;

    @FXML
    private Button add_catdep;

    @FXML
    private Button delete_catdep;

    @FXML
    private Button to_gestion_depense;

    @FXML
    private Button update_catdep;

    public ObservableList<CatDep> CDdata = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        to_gestion_depense.setOnAction(event->to_gestion_depense());
        Refresh.setOnAction(event -> view_categorie_dep());

    }

    private void view_categorie_dep() {
        CatdepService cd = new CatdepService();
        List<CatDep> Categories = cd.getAll();
        CDdata.clear();
        CDdata.addAll(Categories);
        ID.setCellValueFactory(new PropertyValueFactory<CatDep, Integer>("id"));
        Catdep.setCellValueFactory(new PropertyValueFactory<Models.CatDep, String>("type"));
        Table.setItems(CDdata);
    }

    private void to_gestion_depense() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GestiondepenseHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_depense.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
