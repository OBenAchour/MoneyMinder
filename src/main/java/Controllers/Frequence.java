package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transactiontype;

import Services.FrequenceService;
import Services.GestionTransactionsServices.TransactionTypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Frequence {


    @FXML
    private TableColumn<Models.Frequence, String> Frequence;

    @FXML
    private TableColumn<Models.Frequence, Integer> ID;

    @FXML
    private Button Refresh;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TableView<Models.Frequence> Table;

    @FXML
    private URL location;

    @FXML
    private Button to_gestion_transaction;

    @FXML
    private Button add_frequence;

    @FXML
    private Button delete_frequence;

    @FXML
    private TextField ftextfield;

    @FXML
    private Button update_frequence;

    public ObservableList<Models.Frequence> Fdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        to_gestion_transaction.setOnAction(event->to_gestion_transaction());
        Refresh.setOnAction(event -> viewFrequence());
        add_frequence.setOnAction(event -> addFrequence());
        delete_frequence.setOnAction(event -> deleteFrequence());
        update_frequence.setOnAction(event -> updateFrequence());
    }

    private void updateFrequence() {
        Models.Frequence F=Table.getSelectionModel().getSelectedItem();
        FrequenceService tts = new FrequenceService();
        if (F == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            F.setFrequence(ftextfield.getText());
            tts.update(F);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Fréquence modifier avec succées!");
            alert.show();
        }
    }

    private void deleteFrequence() {
        Models.Frequence F=Table.getSelectionModel().getSelectedItem();
        FrequenceService FS=new FrequenceService();
        if (FS==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else {
        FS.delete(F);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("fréquence supprimer avec succées!");
        alert.show();
    }}

    private void addFrequence() {
        String FTF=ftextfield.getText();
        Models.Frequence F=new Models.Frequence(FTF);
        FrequenceService FS=new FrequenceService();
        FS.add(F);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("fréquence ajouter avec succées!");
        alert.show();
    }

    private void viewFrequence() {
        FrequenceService fs = new FrequenceService();
        List<Models.Frequence> Frequences = fs.getAll();
        Fdata.clear();
        Fdata.addAll(Frequences);
        ID.setCellValueFactory(new PropertyValueFactory<Models.Frequence, Integer>("id"));
        Frequence.setCellValueFactory(new PropertyValueFactory<Models.Frequence, String>("frequence"));
        Table.setItems(Fdata);
    }

    private void to_gestion_transaction() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GererTransactionsHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_transaction.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


