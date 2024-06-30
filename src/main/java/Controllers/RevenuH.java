package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transaction;
import Services.GestionTransactionsServices.QuotedepService;
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

public class RevenuH {

    @FXML
    private ResourceBundle resources;
    @FXML
    private TableColumn<Transaction, String> frequence;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Models.Transaction, Integer> Annee;

    @FXML
    private TableColumn<Transaction, String> Commentaire;

    @FXML
    private TableColumn<Transaction, Integer> Mois;

    @FXML
    private TableColumn<Transaction, Float> Montant;

    @FXML
    private TableView<Models.Transaction> Table;

    @FXML
    private TableColumn<Models.Transaction, String> Titre;

    @FXML
    private Button To_Home;

    @FXML
    private Button add_frequence;

    @FXML
    private TableColumn<Transaction, String> cat;

    @FXML
    private Button delete_revenu;

    @FXML
    private Button update_revenu;

    @FXML
    private Button add_revenu;

    public ObservableList<Models.Transaction> Tdata = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        To_Home.setOnAction(event -> To_Home());
        add_revenu.setOnAction(event -> Add_revenu());
        delete_revenu.setOnAction(event -> Delete_revenu());
        update_revenu.setOnAction(event -> update_revenu());
        TransactionService ts = new TransactionService();
        List<Models.Transaction> Transactions = ts.getAll();
        Tdata.clear();
        Tdata.addAll(Transactions);
        Titre.setCellValueFactory(new PropertyValueFactory<Transaction, String>("titre"));
        Montant.setCellValueFactory(new PropertyValueFactory<Transaction, Float>("montant"));
        Mois.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("mois"));
        Annee.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("annee"));
        frequence.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId_freq().getFrequence()));
        cat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCatrev().getType()));

        Commentaire.setCellValueFactory(new PropertyValueFactory<Transaction,String>("commentaire"));
        Table.setItems(Tdata);
    }

    private void update_revenu() {
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

    private void Delete_revenu() {
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

    private void Add_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AddrevenuH.fxml"));
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
