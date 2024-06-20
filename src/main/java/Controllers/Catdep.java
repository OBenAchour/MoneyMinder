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
import javafx.scene.control.*;
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

    @FXML
    private TextArea textfield;


    public ObservableList<CatDep> CDdata = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        to_gestion_depense.setOnAction(event->to_gestion_depense());
        Refresh.setOnAction(event -> view_categorie_dep());
        add_catdep.setOnAction(event ->add_catdep());
        update_catdep.setOnAction(event ->update_catdep());
        delete_catdep.setOnAction(event ->delete_catdep());

    }

    private void delete_catdep() {
        Models.CatDep CD=Table.getSelectionModel().getSelectedItem();
        CatdepService cds = new CatdepService();
        if (CD == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            cds.delete(CD);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Catégorie dépense supprimer avec succées !");
            alert.show();
        }
    }

    private void update_catdep() {
        Models.CatDep CD=Table.getSelectionModel().getSelectedItem();
        CatdepService cds = new CatdepService();
        Models.CatDep CDU = new Models.CatDep();
        if (CD == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            CDU.setType(textfield.getText());
            CDU.setId(CD.getId());
            cds.update(CDU);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Catégorie dépense modifier avec succées!");
            alert.show();
        }
    }

    private void add_catdep() {
        String CatdepTA=textfield.getText();
        Models.CatDep CD=new Models.CatDep(CatdepTA);
        CatdepService CDS=new CatdepService();
        CDS.add(CD);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Catégorie dépense ajouter avec succées !");
        alert.show();
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
