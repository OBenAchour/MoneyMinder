package Controllers;

import Models.Action;
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
import java.util.ArrayList;
import java.util.List;
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



    private List<Action> prepareActionsList() {

        List<Action> actions = new ArrayList<>();

        // Financial Sector
        actions.add(new Action(1, "Amen Bank", 15.30f, 14.50f, 16.00f, 1000, 15.20f, 15.30f, 16.10f, 14.40f, 1.00f, 1200.0f));
        actions.add(new Action(2, "Arab Tunisian Bank", 12.50f, 12.00f, 13.00f, 1500, 12.30f, 12.50f, 13.10f, 11.90f, 0.80f, 1500.0f));
        actions.add(new Action(3, "Banque de l'Habitat", 20.00f, 19.50f, 21.00f, 2000, 19.80f, 20.00f, 21.20f, 19.30f, 1.50f, 1800.0f));
        actions.add(new Action(4, "Banque de Tunisie", 11.75f, 11.00f, 12.50f, 1200, 11.50f, 11.75f, 12.60f, 10.90f, 0.85f, 1300.0f));
        actions.add(new Action(5, "Société Tunisienne de Banque", 9.80f, 9.00f, 10.50f, 1700, 9.60f, 9.80f, 10.60f, 8.90f, 0.70f, 1600.0f));

        // Financial Services
        actions.add(new Action(6, "Arab Tunisian Lease", 25.00f, 24.00f, 26.00f, 800, 24.50f, 25.00f, 26.50f, 23.80f, 1.50f, 900.0f));
        actions.add(new Action(7, "Attijari Leasing", 14.20f, 13.50f, 15.00f, 950, 13.90f, 14.20f, 15.10f, 13.20f, 1.10f, 980.0f));

        // Insurance
        actions.add(new Action(8, "Compagnie d'Assurances et de Réassurrances", 30.00f, 28.50f, 31.50f, 600, 29.50f, 30.00f, 31.80f, 28.00f, 1.80f, 700.0f));
        actions.add(new Action(9, "Société Tunisienne d'Assurances et de Réassurances", 22.50f, 21.00f, 24.00f, 1100, 21.80f, 22.50f, 24.30f, 20.90f, 1.40f, 1150.0f));

        // Telecommunication
        actions.add(new Action(10, "Société Tunisienne d'Entreprises de Telecommunications", 18.75f, 17.50f, 19.50f, 1300, 18.20f, 18.75f, 19.70f, 17.00f, 1.50f, 1400.0f));
        actions.add(new Action(11, "Servicom", 12.00f, 11.00f, 13.00f, 1400, 11.50f, 12.00f, 13.20f, 10.80f, 1.20f, 1450.0f));

        // General Retailers
        actions.add(new Action(12, "Magasin Général", 15.50f, 14.50f, 16.50f, 1000, 15.00f, 15.50f, 16.70f, 14.20f, 1.20f, 1050.0f));
        actions.add(new Action(13, "Société Nouvelle Maison de la Ville de Tunis", 11.20f, 10.50f, 12.00f, 1200, 10.90f, 11.20f, 12.10f, 10.00f, 1.10f, 1300.0f));

        // Healthcare
        actions.add(new Action(14, "Société Adwya", 9.90f, 9.00f, 10.50f, 1600, 9.50f, 9.90f, 10.60f, 8.90f, 1.00f, 1700.0f));
        actions.add(new Action(15, "Société des Industries Pharmaceutiques de Tunisie", 20.30f, 19.00f, 21.50f, 900, 19.80f, 20.30f, 21.70f, 18.90f, 1.40f, 950.0f));

        // Consumer Goods
        actions.add(new Action(16, "Société l'Accumulateur Tunisien", 7.75f, 7.00f, 8.50f, 2000, 7.50f, 7.75f, 8.60f, 6.90f, 0.80f, 2100.0f));
        actions.add(new Action(17, "Société Générale Industrielle de Filtration", 10.50f, 9.50f, 11.50f, 1500, 10.00f, 10.50f, 11.60f, 9.00f, 1.00f, 1600.0f));

        // Food and Beverage
        actions.add(new Action(18, "Société Frigorifique et Brasserie de Tunis", 27.50f, 26.00f, 29.00f, 800, 26.80f, 27.50f, 29.20f, 25.90f, 1.70f, 850.0f));
        actions.add(new Action(19, "Tunisie Lait", 15.80f, 14.50f, 16.50f, 1300, 15.30f, 15.80f, 16.70f, 14.20f, 1.20f, 1400.0f));

        actions.add(new Action(20, "Essoukna", 12.50f, 11.50f, 13.50f, 1100, 12.00f, 12.50f, 13.60f, 11.10f, 1.10f, 1150.0f));
        actions.add(new Action(21, "Carthage Cement", 8.40f, 7.50f, 9.00f, 1900, 8.00f, 8.40f, 9.10f, 7.20f, 1.10f, 2000.0f));

        // Industrial Goods and Services
        actions.add(new Action(22, "One Tech Holding", 19.30f, 18.00f, 20.50f, 1000, 18.80f, 19.30f, 20.60f, 17.90f, 1.50f, 1050.0f));
        actions.add(new Action(23, "Société Tunisienne de Verreries", 10.00f, 9.00f, 11.00f, 1400, 9.50f, 10.00f, 11.10f, 8.80f, 1.00f, 1450.0f));

        // Chemicals
        actions.add(new Action(24, "Air Liquide Tunisie", 22.00f, 21.00f, 23.50f, 900, 21.50f, 22.00f, 23.60f, 20.90f, 1.60f, 950.0f));
        actions.add(new Action(25, "Société Chimique", 18.50f, 17.00f, 19.50f, 1100, 18.00f, 18.50f, 19.60f, 16.90f, 1.40f, 1150.0f));

        return  actions;
    }

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

        tabAction.getItems().addAll(prepareActionsList());


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

    @FXML
    void onVente(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vente.fxml"));
        Pane popupPane = loader.load();
        VenteController controller = loader.getController();
        controller.setAction(action);

        Stage popupStage = new Stage();
        controller.setStage(popupStage);
        popupStage.setTitle("Vente");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene popupScene = new Scene(popupPane);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();

    }


    @FXML
    void navigateToPortefeuille(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnPortefeuille.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/portefeuille.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
