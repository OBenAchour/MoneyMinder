package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Quoterev;
import Services.GestionTransactionsServices.CatrevService;
import Services.GestionTransactionsServices.FrequenceService;
import Services.GestionTransactionsServices.QuoterevService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private TextArea textfield;


    public ObservableList<Models.Catrev> CRdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
       to_gestion_revenu.setOnAction(event ->to_gestion_revenu());
       Refresh.setOnAction(event -> view_Categorie_Revenu());
       add_catrev.setOnAction(event -> add_catrev());
       delete_catrev.setOnAction(event -> delete_catrev());
       update_catrev.setOnAction(event -> update_catrev());
    }

    private void update_catrev() {
        Models.Catrev CR=Table.getSelectionModel().getSelectedItem();
        CatrevService crs = new CatrevService();
        Models.Catrev CRU = new Models.Catrev();
        if (CR == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            CRU.setType(textfield.getText());
            CRU.setId(CR.getId());
            crs.update(CRU);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Catégorie revenu modifier avec succées!");
            alert.show();
        }
    }


    private void delete_catrev() {
        Models.Catrev CR=Table.getSelectionModel().getSelectedItem();
        CatrevService crs = new CatrevService();
        if (CR == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            crs.delete(CR);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Catégorie revenu supprimer avec succées !");
            alert.show();
        }
    }

    private void add_catrev() {
        String CatrevTA=textfield.getText();
        Models.Catrev CR=new Models.Catrev(CatrevTA);
        CatrevService CRS=new CatrevService();
        CRS.add(CR);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Catégorie revenu ajouter avec succées !");
        alert.show();
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
