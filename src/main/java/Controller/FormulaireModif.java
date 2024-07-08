package Controller;

import Models.Catobj;
import Models.Objectif;
import Services.CatobjService;
import Services.ObjectifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FormulaireModif {

    @FXML
    private TextField montantConserveField;

    @FXML
    private TextField titreField;

    @FXML
    private TextField montantGlobaleField;

    @FXML
    private TextField moisField;

    @FXML
    private TextField commentaireField;

    @FXML
    private Button btnretmodif;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnV;

    @FXML
    private ComboBox<String> typecat;

    private Objectif objectif;
    private ObjectifService objectifService = new ObjectifService();

    private CatobjService catobjService = new CatobjService();
    private ObservableList<String> catobjNames = FXCollections.observableArrayList();

    private Runnable onSaveCallback;

    @FXML
    void initialize() {
        btnretmodif.setOnAction(event -> retourModif());
        btnHome.setOnAction(event -> retourHome());
        btnV.setOnAction(event -> validerModif());
        typecat.setItems(CRdata);
    }

    private void retourModif() {
        loadFXML("/AjouterObjectif.fxml");
    }

    private void retourHome() {
        loadFXML("/Home.fxml");
    }

    @FXML
    private void validerModif() {
        if (objectif == null) {
            System.out.println("Erreur : aucun objectif à modifier.");
            return;
        }

        // Récupérer et définir la catégorie depuis le ComboBox
        String cat = typecat.getValue();
        Catobj catobj = new Catobj();
        List<Catobj> catobjs= cr.getbyfilter("catobj",cat);
        catobj.setCatobj(catobjs.get(0).getCatobj());
        catobj.setId_obj(catobjs.get(0).getId_obj());

        // Mettre à jour les autres champs de l'objectif
        objectif.setTitre(titreField.getText());
        objectif.setMontant_globale(parseDoubleOrDefault(montantGlobaleField.getText(), 0.0));
        objectif.setMois(parseIntOrDefault(moisField.getText(), 0));
        objectif.setCommentaire(commentaireField.getText());
        objectif.setCatobj(catobjs.get(0));

        // Mettre à jour le montant conservé si montantConserveField n'est pas vide
        if (!montantConserveField.getText().isEmpty()) {
            double montant_conserve = parseDoubleOrDefault(montantConserveField.getText(),0.0);
            if (objectif.getMontant_conserve() == null) {
                objectif.setMontant_conserve(montant_conserve);
            } else {
                double montantConserveActuel = objectif.getMontant_conserve();
                objectif.setMontant_conserve(montantConserveActuel + montant_conserve);
            }
        }

        try {
            // Mettre à jour l'objectif dans la base de données
            objectifService.update(objectif);
            System.out.println("Objectif mis à jour avec succès !");

            if (onSaveCallback != null) {
                onSaveCallback.run();
            }


            // Charger la vue Home.fxml après la mise à jour
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Si vous avez une TableView dans Home.fxml, vous devriez actualiser son contenu ici
            // Par exemple :
            // HomeController homeController = loader.getController();
            // homeController.actualiserTableView();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Chargement de l'interface avec succès : " + fxmlPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double parseDoubleOrDefault(String text, double defaultValue) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private int parseIntOrDefault(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
        if (objectif != null) {
            titreField.setText(objectif.getTitre());
            montantGlobaleField.setText(String.valueOf(objectif.getMontant_globale()));
            moisField.setText(String.valueOf(objectif.getMois()));
            commentaireField.setText(objectif.getCommentaire());
            montantConserveField.setText(String.valueOf(objectif.getMontant_conserve()));
        } else {
            System.out.println("Erreur : Objectif est null.");
        }
    }


    public void setOnSaveCallback(Runnable onSaveCallback) {
        this.onSaveCallback = onSaveCallback;
    }


    CatobjService cr = new CatobjService();
    List<Catobj> Categories = cr.getAll();
    public ObservableList<String> CRdata = FXCollections.observableArrayList(Categories.stream().map(CR->CR.getCatobj()).toList());



}