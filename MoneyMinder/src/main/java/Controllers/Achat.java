package Controllers;

import Models.AchatAssets;
import Services.ServiceAchatAssets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class Achat {

    @FXML
    private TableColumn<AchatAssets, Integer> idAsset;

    @FXML
    private TableColumn<AchatAssets, String> coloneetitre;

    @FXML
    private TableColumn<AchatAssets, Float> clonneprix;

    @FXML
    private TableView<AchatAssets> achatTableView;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnHome;

    private final ServiceAchatAssets achatService = ServiceAchatAssets.getInstance();

    @FXML
    public void initialize() {
        btnHome.setOnAction(event -> loadHome());
        btnRetour.setOnAction(actionEvent -> btnRetour());
        setupTable();
        loadData();
    }

    private void loadHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
        idAsset.setCellValueFactory(new PropertyValueFactory<>("id_achat"));
        coloneetitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        clonneprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        achatTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadData() {
        ObservableList<AchatAssets> achatList = FXCollections.observableArrayList(achatService.getAll());
        achatTableView.setItems(achatList);
    }
    @FXML
    void btnRetour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panier.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
