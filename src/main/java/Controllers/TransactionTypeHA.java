package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transactiontype;
import Services.GestionTransactionsServices.CatrevService;
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

public class TransactionTypeHA {

    @FXML
    private ResourceBundle resources;

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

    @FXML
    private TableView<Transactiontype> Table;

    @FXML
    private TableColumn<Transactiontype, Integer> ID;

    @FXML
    private TableColumn<Transactiontype, String> Transactiontype;

    @FXML
    private Button Refresh;

    @FXML
    private TextField tttextfield;

    public ObservableList<Transactiontype> TTdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        to_gestion_transaction.setOnAction(event -> to_gestion_transaction());
        Refresh.setOnAction(event -> viewTransactiontype());
        add_transaction_type.setOnAction(event -> addTransactiontype());
        delete_transaction_type.setOnAction(event -> deleteTransactiontype());
        update_transaction_type.setOnAction(event -> updateTransactiontype());
    }

    private void updateTransactiontype() {
        Models.Transactiontype TT=Table.getSelectionModel().getSelectedItem();
        TransactionTypeService tts = new TransactionTypeService();
        if (TT == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            TT.setType(tttextfield.getText());
            tts.update(TT);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("type transaction modifier avec succées!");
            alert.show();
        }
    }

    private void deleteTransactiontype() {
        Models.Transactiontype TT=Table.getSelectionModel().getSelectedItem();
        TransactionTypeService tts = new TransactionTypeService();
        if (TT == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            tts.delete(TT);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("type transaction supprimer avec succées !");
            alert.show();
        }
    }

    private void addTransactiontype() {
        String TTTF=tttextfield.getText();
        Models.Transactiontype TT=new Models.Transactiontype(TTTF);
        TransactionTypeService TTS=new TransactionTypeService();
        TTS.add(TT);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("transaction type ajouter avec succées!");
        alert.show();
    }


    @FXML
    public void viewTransactiontype() {
        TransactionTypeService tts = new TransactionTypeService();
        List<Transactiontype> transactionTypes = tts.getAll();
        TTdata.clear(); // Clear existing data before adding new data
        TTdata.addAll(transactionTypes);
        ID.setCellValueFactory(new PropertyValueFactory<Transactiontype, Integer>("id"));
        Transactiontype.setCellValueFactory(new PropertyValueFactory<Transactiontype, String>("type"));
        Table.setItems(TTdata);
    }

    private void to_gestion_transaction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GererTransactionsHA.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) to_gestion_transaction.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
