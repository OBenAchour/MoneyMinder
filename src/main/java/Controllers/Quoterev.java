package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Frequence;
import Services.GestionTransactionsServices.FrequenceService;
import Services.GestionTransactionsServices.QuoterevService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Quoterev {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Models.Quoterev, Integer> ID;

    @FXML
    private Button Refresh;

    @FXML
    private TableView<Models.Quoterev> Table;

    @FXML
    private TableColumn<Models.Quoterev, String> Quoterev;

    @FXML
    private Button add_quote;

    @FXML
    private Button delete_quote;

    @FXML
    private Button to_gestion_revenu;

    @FXML
    private Button update_quote;


    @FXML
    private TextArea textfield;




    public ObservableList<Models.Quoterev> QRdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        to_gestion_revenu.setOnAction(event->to_gestion_revenu());
        Refresh.setOnAction(event -> viewQuoterev());
        add_quote.setOnAction(event -> add_quote());
        delete_quote.setOnAction(event -> delete_quote());
        update_quote.setOnAction(event -> update_quote());
    }


    private void update_quote() {
        Models.Quoterev QR=Table.getSelectionModel().getSelectedItem();
        QuoterevService qrs = new QuoterevService();
        Models.Quoterev QRU = new Models.Quoterev();
        if (QR == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            QRU.setQuote(textfield.getText());
            QRU.setId(QR.getId());
            qrs.update(QRU);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Quote revenu modifier avec succées!");
            alert.show();
        }
    }

    private void delete_quote() {
        Models.Quoterev QR=Table.getSelectionModel().getSelectedItem();
        QuoterevService qrs = new QuoterevService();
        if (QR == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
            else{
                qrs.delete(QR);
            }

    }

    private void add_quote() {
        String quoterevTA=textfield.getText();
        Models.Quoterev QR=new Models.Quoterev(quoterevTA);
        QuoterevService QRS=new QuoterevService();
        QRS.add(QR);
    }

    private void viewQuoterev() {
        QuoterevService qrs = new QuoterevService();
        List<Models.Quoterev> Quotes = qrs.getAll();
        QRdata.clear();
        QRdata.addAll(Quotes);
        ID.setCellValueFactory(new PropertyValueFactory<Models.Quoterev, Integer>("id"));
        Quoterev.setCellValueFactory(new PropertyValueFactory<Models.Quoterev, String>("quote"));
        Table.setItems(QRdata);
    }

    private void to_gestion_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GestionrevenuHA.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_gestion_revenu.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
