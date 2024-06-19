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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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




    public ObservableList<Models.Quoterev> QRdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        to_gestion_revenu.setOnAction(event->to_gestion_revenu());
        Refresh.setOnAction(event -> viewQuoterev());
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
