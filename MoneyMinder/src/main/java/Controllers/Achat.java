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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class Achat {
    private static final String AI_ENDPOINT = "http://your-ai-service-url";

    Gson gson = new Gson();

    @FXML
    private TableColumn<AchatAssets, Integer> idAsset;

    @FXML
    private TableColumn<AchatAssets, String> coloneetitre;

    @FXML
    private TableColumn<AchatAssets, Float> clonneprix;

    @FXML
    private TableColumn<AchatAssets, String> dateAchat;

    @FXML
    private TableView<AchatAssets> achatTableView;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnHome;

    @FXML
    private TextField totalPriceField;

    @FXML
    private TextField totalMonthPurchaseField;

    private final ServiceAchatAssets achatService = ServiceAchatAssets.getInstance();

    @FXML
    public void initialize() {
        btnHome.setOnAction(event -> loadHome());
        btnRetour.setOnAction(actionEvent -> btnRetour());
        calculateTotalMonthPurchase();
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
        dateAchat.setCellValueFactory(new PropertyValueFactory<>("date_achat"));
        achatTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadData() {
        ObservableList<AchatAssets> achatList = FXCollections.observableArrayList(achatService.getAll());
        achatTableView.setItems(achatList);
        calculateTotalPrice(achatList);
    }

    private void calculateTotalPrice(ObservableList<AchatAssets> achatList) {
        float totalPrice = 0;
        for (AchatAssets asset : achatList) {
            totalPrice += asset.getPrix();
        }
        totalPriceField.setText(String.format("Total Price: %.2f", totalPrice));
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

    private void calculateTotalMonthPurchase() {
        float totalMonthPurchase = achatService.getTotalMonthPurchase();
        totalMonthPurchaseField.setText(String.format("Total Month Purchase: %.2f", totalMonthPurchase));
    }






}
