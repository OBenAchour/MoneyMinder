package Controllers;

import Models.Action;
import Models.Compte;
import Models.Portefeuille;
import Models.Portefeuille_actions;
import Services.CompteService;
import Services.PortefeuilleActionService;
import Utils.UserUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
    private Portefeuille_actions pf;
    private PortefeuilleActionService pfs = new PortefeuilleActionService();
    private   CompteService compteService = new CompteService();
    public void setAction(Portefeuille_actions pf){

        this.pf = pf;

        txtQte.setText(String.valueOf(pf.getQuantite()));
        txtPVente.setText(String.valueOf(pf.getCours()));
        txtTotal.setText(String.valueOf(pf.getQuantite() * pf.getCours()));

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void onCancel(ActionEvent event) {
        stage.close();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.txtQte.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.txtQte.setText(newValue.replaceAll("[^\\d]", ""));
            }
            double qte = 0;
            if (!this.txtQte.getText().isEmpty()) {
                qte = Double.parseDouble(txtQte.getText());
            }
            if (pf != null && qte > pf.getQuantite()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Merci de saisir une quantité inférieure à " + pf.getQuantite());
                alert.showAndWait();
                txtQte.setText(String.valueOf(pf.getQuantite()));
                qte = pf.getQuantite();
            }
            if (pf != null) {
                double totalValue = qte * pf.getCours();
                txtTotal.setText(String.valueOf(BigDecimal.valueOf(totalValue)
                        .setScale(2, BigDecimal.ROUND_HALF_DOWN)
                        .floatValue()));


            } else {
                txtTotal.setText("0");
                System.out.println("Action is null in listener");
            }
        });

    }


    @FXML
    void onOK(ActionEvent event) throws SQLException {
        if (pf != null) {

            Compte compte = compteService.getCompteById(UserUtil.userId);
            pf.setQuantite(pf.getQuantite() -Integer.parseInt(txtQte.getText()) );
            pfs.updateQuantite(pf);

            pf.setQuantite(Integer.parseInt(txtQte.getText()));
            pf.setTotal(Float.parseFloat(txtTotal.getText()));
            pf.setDate_creation(new Date());
            pf.setType("Vente");
            pf.setId_user(UserUtil.userId);
            pfs.add(pf);
            compte.setSolde(compte.getSolde() + Float.parseFloat(txtTotal.getText()));
            compteService.updateCompte(compte);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Vente éfectué avec succès");
            alert.showAndWait();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Action non définie");
            alert.showAndWait();
        }
    }
}
