package Controller;

import Models.Objectif;
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

public class Homee implements Initializable {


    @FXML
    private TableView<Objectif> objectifTable;

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
    private TableColumn<Objectif, Integer> colMois;

    @FXML
    private TableColumn<Objectif, String> colCommentaire;

    private ObjectifService objectifService = new ObjectifService();

    private ObservableList<Objectif> objectifList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        objectifService = new ObjectifService();
        setupTable();
        loadData();
        btnajouter.setOnAction(event -> loadFormulaire());
        btnmodifier.setOnAction(event -> loadFormulaireModif());
        btnSupprimer.setOnAction(event -> deleteObjectif());
        btnHome.setOnAction(event -> retHome());
    }

    private void setupTable() {
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colmontant_globale.setCellValueFactory(new PropertyValueFactory<>("montant_globale"));
        colMois.setCellValueFactory(new PropertyValueFactory<>("mois"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
    }

    private void loadData() {
        List<Objectif> objectifs = objectifService.getAll();
        ObservableList<Objectif> objectifsList = FXCollections.observableArrayList(objectifs);
        tableView.setItems(objectifsList);
    }

    private void loadFormulaire() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Formulaire.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFormulaireModif() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaireModif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void retHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaireModif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deleteObjectif() {
        Objectif selectedObjectif = tableView.getSelectionModel().getSelectedItem();
        if (selectedObjectif != null) {
            objectifService.delete(selectedObjectif);
            tableView.getItems().remove(selectedObjectif);
        }
    }

    private void handleModifierButton(ActionEvent event) {
        Objectif selectedObjectif = objectifTable.getSelectionModel().getSelectedItem();
        if (selectedObjectif != null) {
            openModificationForm(selectedObjectif);
        } else {
            // Handle case when no objectif is selected (e.g., show an alert)
        }
    }

    private void openModificationForm(Objectif objectif) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/FormulaireModif.fxml"));
            Parent root = loader.load();

            FormulaireModif controller = loader.getController();
            controller.setObjectif(objectif);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier Objectif");
            stage.show();

            // Close the current window if needed
            // Stage currentStage = (Stage) btnModifier.getScene().getWindow();
            // currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
