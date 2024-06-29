package Controllers;

import Models.Portefeuille;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.FloatBinding;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

public class PortefeuilleController implements Initializable {

    @FXML
    private TableColumn<Portefeuille, Float> colCours;

    @FXML
    private TableColumn<Portefeuille, String> colNom;

    @FXML
    private TableColumn<Portefeuille, Double> colQuantite;

    @FXML
    private TableColumn<Portefeuille, Float> coltotal;

    @FXML
    private TableView<Portefeuille> tabPortefeuille;
    @FXML
    private Text txtTotal;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        colCours.setCellValueFactory(
                new PropertyValueFactory<>("cours"));

        colQuantite.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));

        coltotal.setCellValueFactory(
                new PropertyValueFactory<>("total"));

        tabPortefeuille.getItems().addAll(preparePortefeuilleList());


        DoubleBinding total = Bindings.createDoubleBinding(() ->
                        tabPortefeuille.getItems().stream().collect(Collectors.summingDouble(Portefeuille::getTotal)),
                tabPortefeuille.getItems());

        txtTotal.setText(String.valueOf(total.get()));
    }



    private List<Portefeuille> preparePortefeuilleList() {

        List<Portefeuille> actions = new ArrayList<>();

        actions.add(new Portefeuille(1,1, "Amen Bank", 15.30f, 20,306 ));
        actions.add(new Portefeuille(2,1, "Arab Tunisian Bank", 12.50f, 200, 2500));
        actions.add(new Portefeuille(3,1, "Banque de l'Habitat", 20.00f,150, 3000));
        actions.add(new Portefeuille(4, 1,"Banque de Tunisie", 11.75f,70, 822.5f));
        actions.add(new Portefeuille(5,1, "Société Tunisienne de Banque", 9.80f,25,245));


        return  actions;
    }
}
