package Controllers;

import Models.Action;
import Models.Compte;
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
    private float vente;
    private String nom;
    private float cours;
    private float total;
    private Stage stage;
    private PortefeuilleActionService pfs = new PortefeuilleActionService();
    private CompteService compteService = new CompteService();

    public void setAction(Action action) {
        this.action = action;
        if (action != null) {
            txtPAchat.setText(String.valueOf(action.getPrix_achat()));
            txtQte.setText("0");
            txtTotal.setText("0");
            this.cours = action.getCours();
            this.nom = action.getNom();
            this.vente = action.getPrix_vente();
            System.out.println("Action set: " + action);
        } else {
            System.out.println("Action is null");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void onCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) throws SQLException {
        if (action != null) {

            Compte compte = compteService.getCompteById(UserUtil.userId);

            if(compte.getSolde() >=   Float.parseFloat(txtTotal.getText()) ){
                Portefeuille_actions pf = new Portefeuille_actions();
                pf.setCours(this.cours);
                pf.setQuantite(Integer.parseInt(txtQte.getText()));
                pf.setNom(this.nom);
                pf.setTotal(Float.parseFloat(txtTotal.getText()));
                pf.setDate_creation(new Date());
                pf.setType("Achat");
                pf.setId_user(UserUtil.userId);
                pfs.add(pf);
                compte.setSolde(compte.getSolde()-  Float.parseFloat(txtTotal.getText()));
                compteService.updateCompte(compte);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Achat éfectué avec succès");
                alert.showAndWait();

                stage.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Solde insuffisant");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Action non définie");
            alert.showAndWait();
        }
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
            if (action != null && qte > action.getQuantite()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Merci de saisir une quantité inférieure à " + action.getQuantite());
                alert.showAndWait();
                txtQte.setText(String.valueOf(action.getQuantite()));
                qte = action.getQuantite();
            }
            if (action != null) {
                double totalValue = qte * action.getPrix_achat();
                txtTotal.setText(String.valueOf(BigDecimal.valueOf(totalValue)
                        .setScale(2, BigDecimal.ROUND_HALF_DOWN)
                        .floatValue()));
                System.out.println("Quantité: " + qte + ", Prix achat: " + action.getPrix_achat() + ", Total: " + totalValue);
            } else {
                txtTotal.setText("0");
                System.out.println("Action is null in listener");
            }
        });
    }
}
