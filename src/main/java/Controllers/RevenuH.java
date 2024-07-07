package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Models.Transaction;
import Models.Transactiontype;
import Models.User;
import Services.GestionTransactionsServices.CatdepService;
import Services.GestionTransactionsServices.QuotedepService;
import Services.GestionTransactionsServices.TransactionService;
import Services.GestionTransactionsServices.TransactionTypeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public TableView<Models.Transaction> Table;

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

    User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");



    @FXML
    void initialize() {
        To_Home.setOnAction(event -> To_Home());
        add_revenu.setOnAction(event -> Add_revenu());
        delete_revenu.setOnAction(event -> Delete_revenu());
        update_revenu.setOnAction(event -> update_revenu());
        TransactionService ts = new TransactionService();
        List<Models.Transaction> Transactions = ts.getbyfilter("where type=2 and id_user=" + u.getId() );
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
        Transaction T = Table.getSelectionModel().getSelectedItem();
        if (T == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sélectionnez une ligne à modifier !");
            alert.show();
            return;
        }

        try {
            // Get the controller of UpdaterevenuH

            UpdaterevenuH UR = new UpdaterevenuH();//loader.getController();
            UR.setVT(T);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdaterevenuH.fxml"));
            loader.setController(UR);
            Parent root = loader.load();


            Stage stage = (Stage) update_revenu.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Delete_revenu() {
        Transaction T=Table.getSelectionModel().getSelectedItem();
        TransactionService TS = new TransactionService();
        if (T == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            TS.delete(T);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Revenu supprimer avec succées !");
            alert.show();
            initialize();
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
