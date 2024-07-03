package Controllers;

import Models.Panier;
import Services.ServiceAchatAssets;
import Services.ServicePanier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class PanierController {

    @FXML
    private TableColumn<Panier, Integer> idAsset;

    @FXML
    private TableColumn<Panier, String> coloneetitre;

    @FXML
    private TableColumn<Panier, Float> clonneprix;

    @FXML
    private TableView<Panier> panierTableView;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnsupp;

    @FXML
    private Button btnValidate;


    private final ServicePanier panierService = ServicePanier.getInstance();
   private final ServiceAchatAssets achatService = ServiceAchatAssets.getInstance();


    @FXML
    public void initialize() {
        btnHome.setOnAction(event -> loadHome());
        btnsupp.setOnAction(event -> supprimerDuPanier());
        btnValidate.setOnAction(event -> validerAchat());
        btnRetour.setOnAction(actionEvent -> btnretour());
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
        idAsset.setCellValueFactory(new PropertyValueFactory<>("idAssets"));
        coloneetitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        clonneprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        panierTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadData() {
        ObservableList<Panier> panierList = FXCollections.observableArrayList(panierService.getAll());
        panierTableView.setItems(panierList);
    }

    private void supprimerDuPanier() {
        Panier selectedAsset = panierTableView.getSelectionModel().getSelectedItem();
        if (selectedAsset != null) {
            panierService.remove(selectedAsset);
            loadData();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'asset a été supprimé du panier !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un asset à supprimer du panier.");
            alert.showAndWait();
        }
    }

    private void validerAchat() {
        Panier selectedAsset = panierTableView.getSelectionModel().getSelectedItem();
        if (selectedAsset != null) {
            achatService.add(selectedAsset); // Add to "Achat" table
            panierService.remove(selectedAsset); // Remove from "Panier"
            loadData();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'asset a été validé et ajouté aux achats !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un asset à valider.");
            alert.showAndWait();
        }
    }
    @FXML
    void btnretour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Assets.fxml"));
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
