package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Quoterev;
import Services.GestionTransactionsServices.QuotedepService;
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

public class Quotedep {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Models.Quotedep, Integer> ID;

    @FXML
    private TableColumn<Models.Quotedep, String> Quotedep;

    @FXML
    private Button Refresh;

    @FXML
    private TableView<Models.Quotedep> Table;

    @FXML
    private Button add_quote;

    @FXML
    private Button delete_quote;

    @FXML
    private Button to_gestion_revenu;

    @FXML
    private Button update_quote;

    public ObservableList<Models.Quotedep> QDdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
       to_gestion_revenu.setOnAction(event -> to_gestion_revenu());
       Refresh.setOnAction(event -> viewQuoteDep());
    }

    private void viewQuoteDep() {
        QuotedepService qds = new QuotedepService();
        List<Models.Quotedep> Quotes = qds.getAll();
        QDdata.clear();
        QDdata.addAll(Quotes);
        ID.setCellValueFactory(new PropertyValueFactory<Models.Quotedep, Integer>("id"));
        Quotedep.setCellValueFactory(new PropertyValueFactory<Models.Quotedep, String>("quote"));
        Table.setItems(QDdata);
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
