package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Models.Transaction;
import Models.User;
import Services.GestionTransactionsServices.TransactionService;
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
    private Button To_Dashboard;

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

    User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");
    @FXML
    void initialize() {
        To_Dashboard.setOnAction(event ->To_Dashboard());
        update_depense.setOnAction(event -> update_depense());
        To_Home.setOnAction(event -> To_Home());
        add_depense.setOnAction(event -> add_depense());
        delete_depense.setOnAction(event -> delete_depense());
        TransactionService ts = new TransactionService();
        List<Transaction> Transactions = ts.getbyfilter("where type=1 and id_user=" + u.getId());
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

    private void To_Dashboard() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Dashboardoutcome.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)To_Dashboard.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update_depense() {
        Transaction T = Table.getSelectionModel().getSelectedItem();
        if (T == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sélectionnez une ligne à modifier !");
            alert.show();
            return;
        }

        try {
            // Get the controller of UpdaterevenuH

            UpdatedepenseH UD = new UpdatedepenseH();//loader.getController();
            UD.setVT(T);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatedepenseH.fxml"));
            loader.setController(UD);
            Parent root = loader.load();


            Stage stage = (Stage) update_depense.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete_depense() {
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

    private void add_depense() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AddDepenseH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)add_depense.getScene().getWindow();
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
