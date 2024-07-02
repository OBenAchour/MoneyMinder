package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transaction;
import Services.GestionTransactionsServices.TransactionService;
import javafx.beans.property.SimpleStringProperty;
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

public class DepenseH {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Transaction, Integer> Annee;

    @FXML
    private TableColumn<Transaction, String> Commentaire;

    @FXML
    private TableColumn<Transaction, Integer> Mois;

    @FXML
    private TableColumn<Transaction, Float> Montant;

    @FXML
    private TableView<Transaction> Table;

    @FXML
    private TableColumn<Transaction, String> Titre;

    @FXML
    private Button To_Home;

    @FXML
    private Button add_depense;

    @FXML
    private TableColumn<Transaction,String> cat;

    @FXML
    private Button delete_depense;

    @FXML
    private TableColumn<Transaction,String> frequence;

    @FXML
    private Button update_depense;

    public ObservableList<Transaction> Tdata = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        To_Home.setOnAction(event -> To_Home());
        TransactionService ts = new TransactionService();
        List<Transaction> Transactions = ts.getbyfilter("where type=1");
        Tdata.clear();
        Tdata.addAll(Transactions);
        Titre.setCellValueFactory(new PropertyValueFactory<Transaction, String>("titre"));
        Montant.setCellValueFactory(new PropertyValueFactory<Transaction, Float>("montant"));
        Mois.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("mois"));
        Annee.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("annee"));
        frequence.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId_freq().getFrequence()));
        cat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCatDep().getType()));
        Commentaire.setCellValueFactory(new PropertyValueFactory<Transaction,String>("commentaire"));
        Table.setItems(Tdata);

    }

    private void To_Home() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Home.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)To_Home.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
