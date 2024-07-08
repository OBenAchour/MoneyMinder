package Controller;

import Models.Catobj;
import Models.Objectif;
import Services.ObjectifService;
import javafx.beans.property.SimpleStringProperty;
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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Homee implements Initializable {

//    @FXML
//    private TableView<Objectif> objectifTable;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnHome;

    @FXML
    private TableView<Objectif> tableView;

    @FXML
    private TableColumn<Objectif, String> colTitre;

    @FXML
    private TableColumn<Objectif, Double> colmontant_globale;

    @FXML
    private TableColumn<Objectif, Double> colMontant_conserve;

    @FXML
    private TableColumn<Objectif, Integer> colMois;

    @FXML
    private TableColumn<Objectif, String> colType;

    @FXML
    private TableColumn<Objectif, String> colCommentaire;

    private ObjectifService objectifService = new ObjectifService();

    private ObservableList<Objectif> objectifList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
        btnajouter.setOnAction(event -> loadFormulaire());
        btnmodifier.setOnAction(event -> handleModifierButton());
        btnSupprimer.setOnAction(event -> supprimerType());
        btnHome.setOnAction(event -> retHome());

    }


    private void setupTable() {
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colmontant_globale.setCellValueFactory(new PropertyValueFactory<>("montant_globale"));
        colMois.setCellValueFactory(new PropertyValueFactory<>("mois"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        colMontant_conserve.setCellValueFactory(new PropertyValueFactory<>("montant_conserve"));
        colType.setCellValueFactory(cellData -> {
            Objectif objectif = cellData.getValue();
            return new SimpleStringProperty(objectif.getCatobjName());
        });
    }

    private void loadData() {
        List<Objectif> objectifs = objectifService.getAll();
        objectifs.forEach((e)->{
            System.out.println(e.toString());
        });

        ObservableList<Objectif> objectifsList = FXCollections.observableArrayList(objectifs);
        tableView.setItems(objectifsList);
    }
    private void loadFormulaire() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Formulaire.fxml"));
            Parent root = loader.load();
            Formulaire controller = loader.getController();
            controller.setHomeController(this);
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEcheanceAlert(Objectif objectif) {
        double echeance = calculerEcheance(objectif.getMontant_globale(), objectif.getMois());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat de l'échéance");
        alert.setHeaderText(null);
        alert.setContentText("L'échéance calculée est : " + echeance);
        alert.showAndWait();

        loadData();
    }
    public double calculerEcheance(double M_Total, int mois) {
        return M_Total / mois;
    }





    private void retHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void handleModifierButton() {
        Objectif selectedObjectif = tableView.getSelectionModel().getSelectedItem();
        if (selectedObjectif != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaireModif.fxml"));
                Parent root = loader.load();

                FormulaireModif controller = loader.getController();
                controller.setObjectif(selectedObjectif);
                controller.setOnSaveCallback(this::loadData);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun objectif sélectionné pour modification.");
        }
    }

//    private void openModificationForm(Objectif objectif) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaireModif.fxml"));
//            Parent root = loader.load();
//
//            FormulaireModif controller = loader.getController();
//            controller.setObjectif(objectif);
//            controller.setOnSaveCallback(this::loadData);
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Modifier Objectif");
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void supprimerType() {
        Objectif selectedObjectif = tableView.getSelectionModel().getSelectedItem();
        if (selectedObjectif != null) {
            objectifService.delete(selectedObjectif);
            tableView.getItems().remove(selectedObjectif);
        }
    }
}
