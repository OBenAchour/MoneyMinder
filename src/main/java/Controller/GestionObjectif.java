package Controller;

import Models.Catobj;
import Models.Objectif;
import Services.CatobjService;
import Services.ObjectifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionObjectif implements Initializable {

    @FXML
    private TableView<Catobj> tableView;

    @FXML
    private TableColumn<Catobj, String> colcatobj;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnHomeAd;

    private CatobjService catobjService = new CatobjService(); // Create an instance of CatobjService

    private ObservableList<Catobj> catobjList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing GestionObjectif controller");
        btnAjouter.setOnAction(event -> loadAjouterType());
        btnModifier.setOnAction(event -> loadModifierType());
        btnHomeAd.setOnAction(event -> loadGestionObjectif());
        setupTable();
        loadData();
        catobjService = new CatobjService();
    }
    private void setupTable() {
        colcatobj.setCellValueFactory(new PropertyValueFactory<>("catobj"));
    }

//    private void loadData() {
//        System.out.println("Loading data into TableView");
//        List<Catobj> catobjs = catobjService.getAll();
//        catobjList = FXCollections.observableArrayList(catobjs);
//        if (tableView != null) {
//            tableView.setItems(catobjList);
//        } else {
//            System.out.println("tableView is null");
//        }
//    }
    private void loadData() {
        List<Catobj> catobjs = catobjService.getAll();
        ObservableList<Catobj> catobjList = FXCollections.observableArrayList(catobjs);
        tableView.setItems(catobjList);
    }

    private void loadAjouterType() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnAjouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading AjouterType.fxml: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private void loadModifierType() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierType.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGestionObjectif() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnHomeAd.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void deleteCatégorie() {
        Catobj selectedCatobj = tableView.getSelectionModel().getSelectedItem();
        if (selectedCatobj != null) {
            catobjService.delete(selectedCatobj);
            tableView.getItems().remove(selectedCatobj);
        }
    }
}
