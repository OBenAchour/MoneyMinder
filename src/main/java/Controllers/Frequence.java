package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transactiontype;
import Services.GestionTransactionsServices.FrequenceService;
import Services.GestionTransactionsServices.TransactionTypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button add_transaction_type;

    @FXML
    private Button delete_transaction_type;

    @FXML
    private Button to_gestion_transaction;

    @FXML
    private Button update_transaction_type;

    public ObservableList<Models.Frequence> TTdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        to_gestion_transaction.setOnAction(event->to_gestion_transaction());
        Refresh.setOnAction(event -> viewFrequence());

    }

    private void viewFrequence() {
        FrequenceService fs = new FrequenceService();
        List<Models.Frequence> Frequences = fs.getAll();
        TTdata.clear();
        TTdata.addAll(Frequences);
        ID.setCellValueFactory(new PropertyValueFactory<Models.Frequence, Integer>("id"));
        Frequence.setCellValueFactory(new PropertyValueFactory<Models.Frequence, String>("frequence"));
        Table.setItems(TTdata);
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


