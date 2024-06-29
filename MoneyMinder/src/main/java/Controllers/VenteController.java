package Controllers;

import Models.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VenteController implements Initializable {


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private TextField txtPVente;

    @FXML
    private TextField txtQte;

    @FXML
    private TextField txtTotal;
    private Stage stage;
    private Action action;
    public void setAction(Action action){
        this.action = action;
        txtPVente.setText(String.valueOf(action.getPrix_vente()));

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
//        this.txtQte.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*")) {
//                this.txtQte.setText(newValue.replaceAll("[^\\d]", ""));
//            }
//        });



    }
}
