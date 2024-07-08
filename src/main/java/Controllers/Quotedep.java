package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.Quoterev;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private TextArea textfield;

    public ObservableList<Models.Quotedep> QDdata = FXCollections.observableArrayList();

    @FXML
    void initialize() {
       to_gestion_revenu.setOnAction(event -> to_gestion_revenu());
       Refresh.setOnAction(event -> viewQuoteDep());
       add_quote.setOnAction(event -> add_quote());
       delete_quote.setOnAction(event -> delete_quote());
       update_quote.setOnAction(event -> update_quote());
    }

    private void update_quote() {
        Models.Quotedep QD=Table.getSelectionModel().getSelectedItem();
        Services.QuotedepService qds = new Services.QuotedepService();
        Models.Quotedep QDU = new Models.Quotedep();
        if (QD == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            QDU.setQuote(textfield.getText());
            QDU.setId(QD.getId());
            qds.update(QDU);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Quote revenu modifier avec succées!");
            alert.show();
        }
    }

    private void delete_quote() {
        Models.Quotedep QD=Table.getSelectionModel().getSelectedItem();
        Services.QuotedepService qds = new Services.QuotedepService();
        if (QD == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("aucune ligne n'est selectionner !");
            alert.show();
        }
        else{
            qds.delete(QD);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Quote dépense supprimée avec succées!");
            alert.show();
        }
    }

    private void add_quote() {
        String quotedepTA=textfield.getText();
        Models.Quotedep QD=new Models.Quotedep(quotedepTA);
        Services.QuotedepService QDS=new Services.QuotedepService();
        QDS.add(QD);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Quote dépense ajouter avec succées!");
        alert.show();
    }

    private void viewQuoteDep() {
        Services.QuotedepService qds = new Services.QuotedepService();
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
