package Controller;

import Models.Catobj;
import Models.Objectif;
import Services.CatobjService;
import Services.ObjectifService;
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

import java.io.IOException;
import java.util.List;

public class HomeAdmin {

    @FXML
    private Button btnHomeAd;

    @FXML
    private TableView<Catobj> tableView;

    @FXML
    private TableColumn<Catobj, String> colTitre;

    private CatobjService Catserv = new CatobjService();

    private ObservableList<Catobj> catobjList;

    @FXML
    public void initialize() {
        btnHomeAd.setOnAction(event -> loadGestionObjectif());
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



    private void setupTable() {
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));

    }
//    private void loadData() {
//        List<Catobj> catobjList = CatobjService.getAll();
//        ObservableList<Catobj> catobjList = FXCollections.observableArrayList(catobjList);
//        tableView.setItems(catobjList);
//    }
}
