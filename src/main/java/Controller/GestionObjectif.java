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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionObjectif implements Initializable {

    @FXML
    private TableView<Catobj> tableView;

    @FXML
    private TableColumn<Catobj, String> colcatobj;

//    @FXML
//    private TableColumn<Catobj, String> colId;
//
//    @FXML
//    private TableColumn<Catobj, String> colMontant;
//
//    @FXML
//    private TableColumn<Catobj, String> colEcheance;
//
//    @FXML
//    private TableColumn<Catobj, String> colMois;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    private CatobjService catobjService = new CatobjService();

    private ObservableList<Catobj> catobjobservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        loadData();
        btnBack.setOnAction(event -> goBack());
        btnAjouter.setOnAction(event -> ajouterType());
        btnModifier.setOnAction(event -> modifierType());
        btnSupprimer.setOnAction(event -> supprimerType());
        catobjService = new CatobjService();

    }


    private void setupTable() {
        colcatobj.setCellValueFactory(new PropertyValueFactory<>("Catobj"));
    }

    private void loadData() {
        List<Catobj> catobjs = catobjService.getAll();
        ObservableList<Catobj> catobjobservableList = FXCollections.observableArrayList(catobjs);
        tableView.setItems(catobjobservableList);
    }

    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  private void ajouterType() {
      try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterTypee.fxml"));
          Parent root = loader.load();
          Stage stage = (Stage) btnAjouter.getScene().getWindow();
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
      } catch (IOException e) {
          e.printStackTrace();
      }

    }

    private void handleModifierButton(ActionEvent event) {
        Catobj selectedcatobj = tableView.getSelectionModel().getSelectedItem();
        if (selectedcatobj != null) {
            modifierType();
        }
    }

    private void modifierType() {
        Catobj selectedcatobj = tableView.getSelectionModel().getSelectedItem();
        if (selectedcatobj != null) {
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
        } else {
            System.out.println("Aucune catégorie sélectionné pour modification.");
        }
    }

//    private void loadModifierType() {
//        Catobj selectedcatobj = tableView.getSelectionModel().getSelectedItem();
//        if (selectedcatobj != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierType.fxml"));
//                Parent root = loader.load();
//
//                ModifierType controller = loader.getController();
//                controller.setCatobj(selectedcatobj);
//
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Modifier Catégorie");
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Aucune catégorie sélectionné pour modification.");
//        }
//    }


    private void supprimerType() {
        Catobj selectedCatobj = tableView.getSelectionModel().getSelectedItem();
        if (selectedCatobj != null) {
            catobjService.delete(selectedCatobj);
            tableView.getItems().remove(selectedCatobj);
        }
    }


}
