package Controllers;

import Models.Action;
import Models.Compte;
import Models.Portefeuille;
import Models.Portefeuille_actions;
import Services.ActionService;
import Services.CompteService;
import Services.PortefeuilleActionService;
import Utils.UserUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.FloatBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import java.util.stream.Collectors;

public class PortefeuilleController implements Initializable {
    public Portefeuille_actions action;
    private PortefeuilleActionService portefeuilleActionService = new PortefeuilleActionService();


    @FXML
    private Button btnRetour;
    @FXML
    private Button btnVente;

    @FXML
    private TableColumn<Portefeuille_actions, Float> colCours;

    @FXML
    private TableColumn<Portefeuille_actions, String> colNom;

    @FXML
    private TableColumn<Portefeuille_actions, Double> colQuantite;

    @FXML
    private TableColumn<Portefeuille_actions, Float> coltotal;
    @FXML
    private TableColumn<Portefeuille_actions, Date> colDate;

    @FXML
    private TableColumn<Portefeuille_actions, String> colType;


    @FXML
    private TableView<Portefeuille_actions> tabPortefeuille;
    @FXML
    private Text txtTotal;

    @FXML
    private Text txtSolde;
    private PortefeuilleActionService pfs = new PortefeuilleActionService();
    private CompteService compteService = new CompteService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnVente.setDisable(true);
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        colCours.setCellValueFactory(
                new PropertyValueFactory<>("cours"));

        colQuantite.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));

        coltotal.setCellValueFactory(
                new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<>("date_creation"));

        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        try {
            tabPortefeuille.getItems().addAll(portefeuilleActionService.getAll());
            Compte compte = compteService.getCompteById(UserUtil.userId);
            txtSolde.setText(String.valueOf(compte.getSolde()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DoubleBinding totalAchat = Bindings.createDoubleBinding(() ->
                        tabPortefeuille.getItems().stream().filter(action -> action.getType().equals("Achat"))
                                .collect(Collectors.summingDouble(Portefeuille_actions::getTotal)),
                tabPortefeuille.getItems());
        DoubleBinding totalVente = Bindings.createDoubleBinding(() ->
                        tabPortefeuille.getItems().stream().filter(action -> action.getType().equals("Vente"))
                                .collect(Collectors.summingDouble(Portefeuille_actions::getTotal)),
                tabPortefeuille.getItems());
      double total =  totalVente.get() - totalAchat.get();
        txtTotal.setText(String.valueOf( BigDecimal.valueOf(total)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .floatValue()));

//        tabPortefeuille.setRowFactory(tb -> new TableRow<>(){
//            @Override
//            protected void updateItem(Portefeuille_actions item, boolean b) {
//                if(item!=null && item.getQuantite() ==0) {
//                    setStyle("-fx-background-color: tomato;");
//                }else {
//                    setStyle("");
//                }
//            }
//        });

        colType.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                if (s == null || b) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(s);
                    // Apply different colors based on the item value
                    switch (s) {
                        case "Achat":
                            setStyle("-fx-text-fill: red;");
                            break;
                        case "Vente":
                            setStyle("-fx-text-fill: green;");
                            break;
                        default:
                            setStyle("");
                            break;
                    }
                }
            }
        });


        tabPortefeuille.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Portefeuille_actions>() {
            @Override
            public void changed(ObservableValue<? extends Portefeuille_actions> observableValue, Portefeuille_actions portefeuilleActions, Portefeuille_actions t1) {
                action=t1;
                if(action !=null && action.getType().equals("Achat") && action.getQuantite()>0) {
                    btnVente.setDisable(false);
                }else {
                    btnVente.setDisable(true);

                }
            }

        });
    }



    private List<Portefeuille_actions> preparePortefeuilleList() {

        List<Portefeuille_actions> actions = new ArrayList<>();

        return  actions;
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
    void onRetour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/actions.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRefresh(ActionEvent event) throws SQLException {
        tabPortefeuille.getItems().clear();
        tabPortefeuille.getItems().addAll(portefeuilleActionService.getAll());

        DoubleBinding totalAchat = Bindings.createDoubleBinding(() ->
                        tabPortefeuille.getItems().stream().filter(action -> action.getType().equals("Achat"))
                                .collect(Collectors.summingDouble(Portefeuille_actions::getTotal)),
                tabPortefeuille.getItems());
        DoubleBinding totalVente = Bindings.createDoubleBinding(() ->
                        tabPortefeuille.getItems().stream().filter(action -> action.getType().equals("Vente"))
                                .collect(Collectors.summingDouble(Portefeuille_actions::getTotal)),
                tabPortefeuille.getItems());
        double total =  totalVente.get() - totalAchat.get();
        txtTotal.setText(String.valueOf( BigDecimal.valueOf(total)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .floatValue()));

        Compte compte = compteService.getCompteById(UserUtil.userId);
        txtSolde.setText(String.valueOf(compte.getSolde()));
    }
}
