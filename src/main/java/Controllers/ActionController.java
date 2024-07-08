package Controllers;

import Models.Action;
import Models.Compte;
import Models.User;
import Services.ActionService;
import Services.CompteService;
import Services.UserServices;
import Utils.UserSession;
import Utils.UserUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ActionController implements Initializable {
    @FXML
    private Button btnPortefeuille;

    @FXML
    private TableColumn<Action, Float> colAchat;

    @FXML
    private TableColumn<Action, Float> colCours;

    @FXML
    private TableColumn<Action, String> colNom;

    @FXML
    private TableColumn<Action, Float> colVente;

    @FXML
    private TableColumn<Action, Integer> colQuantite;

    @FXML
    private TableView<Action> tabAction;
    @FXML
    private AnchorPane panelDetail;

    @FXML
    private Text txtBas;

    @FXML
    private Button btnBack;

    @FXML
    private Text txtCloture;

    @FXML
    private Text txtHaut;

    @FXML
    private Text txtOuverture;

    @FXML
    private Text txtQteEchange;

    @FXML
    private Text txtVariation;

    @FXML
    private Button btnportefeuille;

    @FXML
    private Button btnsolde;

    @FXML
    private Button btnmesactions;

    public Action action;

    private ActionService actionService = new ActionService();
    private CompteService compteService = new CompteService();

    @FXML
    private Text txtSolde;
    private User u =null;

    public void setU(User u) {
        this.u = u;
    }

    private UserServices userServices;
    private User currentUser;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        panelDetail.setVisible(false);
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        colCours.setCellValueFactory(
                new PropertyValueFactory<>("cours"));
        colAchat.setCellValueFactory(
                new PropertyValueFactory<>("prix_achat"));
        colVente.setCellValueFactory(
                new PropertyValueFactory<>("prix_vente"));
        colQuantite.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));

        try {
            tabAction.getItems().addAll(actionService.getAllActions());
            Compte compte = compteService.getCompteById(UserUtil.userId);
            txtSolde.setText(String.valueOf(compte.getSolde()));
        } catch (SQLException e) {
          e.printStackTrace();
        }


        tabAction.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observableValue, Action old, Action t1) {
                panelDetail.setVisible(true);
                txtOuverture.setText(String.valueOf(t1.getOuverture()));
                txtCloture.setText(String.valueOf(t1.getCloture()));
                txtHaut.setText(String.valueOf(t1.getPrix_haut()));
                txtBas.setText(String.valueOf(t1.getPrix_bas()));
                txtVariation.setText(String.valueOf(t1.getVariation()));
                txtQteEchange.setText(String.valueOf(t1.getQuantite_echange()));
                action = t1;
            }
        });
    }

    @FXML
    private void  btnportefeuille(ActionEvent event) {
        // Affiche les deux autres boutons lorsque le bouton principal est cliqué
        btnsolde.setVisible(true);
        btnmesactions.setVisible(true);
    }



    @FXML
    void onAchat(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Achat.fxml"));
        Pane popupPane = loader.load();
        AchatController controller = loader.getController();
        controller.setAction(action);
        Stage popupStage = new Stage();
        controller.setStage(popupStage);
        popupStage.setTitle("Achat");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene popupScene = new Scene(popupPane);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

//    @FXML
//    void onVente(ActionEvent event) throws IOException {
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vente.fxml"));
//        Pane popupPane = loader.load();
//        VenteController controller = loader.getController();
//        controller.setAction(action);
//
//        Stage popupStage = new Stage();
//        controller.setStage(popupStage);
//        popupStage.setTitle("Vente");
//        popupStage.initModality(Modality.APPLICATION_MODAL);
//        Scene popupScene = new Scene(popupPane);
//        popupStage.setScene(popupScene);
//        popupStage.showAndWait();
//
//    }


    @FXML
    void navigateToPortefeuille(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnPortefeuille.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/portefeuille.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
