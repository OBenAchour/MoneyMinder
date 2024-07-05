package Controllers;

import Models.Assets;
import Models.CatAssets;
import Models.Panier;
import Services.ServiceAchatAssets;
import Services.ServiceCatAssets;
import Services.Serviceassets;
import Services.ServicePanier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AssetsController {

    @FXML
    private TableColumn<Assets, Integer> colidAssets;

    @FXML
    private TableColumn<Assets, String> colTitre;

    @FXML
    private TableColumn<Assets, Double> colPrix;

    @FXML
    private TableColumn<Assets, Integer> colcateg;

    private ObservableList<Assets> AssetsList;

    @FXML
    private TableView<Assets> AssetsTable;

    private Serviceassets AS = new Serviceassets();
    private ServicePanier panierService = ServicePanier.getInstance();

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPanier;

    @FXML
    private Button btnAchat;

    @FXML
    private Button btnAjouterPanier;

    @FXML
    private ComboBox<String> listecateg;

    private ServiceCatAssets sc = new ServiceCatAssets();

    @FXML
    public void initialize() {
        btnHome.setOnAction(event -> loadHome());
        btnPanier.setOnAction(event -> loadPanier());
        btnAchat.setOnAction(event -> loadAchat());
        btnAjouterPanier.setOnAction(event -> ajouterAuPanier());

        // Populate the ComboBox with category titles
        populateCategoryComboBox();

        // Add listener to ComboBox for category selection
        listecateg.setOnAction(event -> filterAssetsByCategory());

        setupTable();
        loadData();
    }

    private void populateCategoryComboBox() {
        // Fetch categories
        List<CatAssets> catAssets = sc.getAll();

        // Print the fetched categories for debugging
        System.out.println("Fetched Categories: " + catAssets);

        // Extract titles
        List<String> catTitles = catAssets.stream()
                .map(CatAssets::getCateg)
                .collect(Collectors.toList());

        // Print the extracted titles for debugging
        System.out.println("Category Titles: " + catTitles);

        // Set items to ComboBox
        ObservableList<String> cdata = FXCollections.observableArrayList(catTitles);
        listecateg.setItems(cdata);

        // Print statement to confirm setting items
        System.out.println("ComboBox Items Set: " + cdata);
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

    private void loadPanier() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panier.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnPanier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAchat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/achat.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnAchat.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
        colidAssets.setCellValueFactory(new PropertyValueFactory<>("id_assets"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colcateg.setCellValueFactory(new PropertyValueFactory<>("id_categ"));

        AssetsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadData() {
        List<Assets> assets = AS.getAll();
        AssetsList = FXCollections.observableArrayList(assets);
        AssetsTable.setItems(AssetsList);
    }

    private void ajouterAuPanier() {
        Assets selectedAsset = AssetsTable.getSelectionModel().getSelectedItem();
        if (selectedAsset != null) {
            // Create a new Panier item based on selected Asset
            Panier panierItem = new Panier(selectedAsset.getId_assets(), selectedAsset.getTitre(), selectedAsset.getPrix());

            // Add the Panier item to the cart
            panierService.add(panierItem);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'asset a été ajouté au panier !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un asset à ajouter au panier.");
            alert.showAndWait();
        }
    }

    private void filterAssetsByCategory() {
        String selectedCategory = listecateg.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            List<Assets> filteredAssets = AS.getAll().stream()
                    .filter(asset -> {
                        CatAssets catAsset = sc.getbyid(asset.getId_categ()).get(0);
                        return catAsset.getCateg().equals(selectedCategory);
                    })
                    .collect(Collectors.toList());

            // Update the table with filtered assets
            AssetsList.setAll(filteredAssets); // Use setAll instead of creating a new list
        }
    }
}
