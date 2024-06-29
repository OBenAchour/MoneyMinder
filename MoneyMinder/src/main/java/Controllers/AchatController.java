package Controllers;

import Models.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AchatController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private TextField txtDuree;

    @FXML
    private TextField txtPAchat;

    @FXML
    private TextField txtQte;

    @FXML
    private TextField txtTotal;
     private Action action;
     private Stage stage;
    public void setAction(Action action){
        this.action = action;
        txtPAchat.setText(String.valueOf(action.getPrix_achat()));
        txtQte.setText(String.valueOf(action.getQuantite()));
        txtTotal.setText(String.valueOf(action.getPrix_achat() * action.getQuantite()));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void onCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.txtQte.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.txtQte.setText(newValue.replaceAll("[^\\d]", ""));
            }
            double qte = 0;
            if(!this.txtQte.getText().isEmpty()) {
              qte   = Double.valueOf(this.txtQte.getText());
            }
            if ( qte> action.getQuantite()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Merci de saisir une quantité inférieur a "+ action.getQuantite());
                alert.showAndWait();
                txtQte.setText(String.valueOf(action.getQuantite()));
                qte = action.getQuantite();
            }
            txtTotal.setText(String.valueOf(qte* action.getPrix_achat()));

        });

        this.txtDuree.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.txtDuree.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


    }
}
