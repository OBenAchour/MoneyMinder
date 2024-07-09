package Controller;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Models.Catobj;
import Models.Objectif;
import Services.CatobjService;
import Services.ObjectifService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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


    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";
    //private static final String FROM_PHONE_NUMBER = "your_twilio_phone_number";

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

        String cat = typecat.getValue();
        Catobj catobj = new Catobj();
        List<Catobj> catobjs= cr.getbyfilter("catobj",cat);
        catobj.setCatobj(catobjs.get(0).getCatobj());
        catobj.setId_obj(catobjs.get(0).getId_obj());


        objectif.setTitre(titreField.getText());
        objectif.setMontant_globale(parseDoubleOrDefault(montantGlobaleField.getText(), 0.0));
        objectif.setMois(parseIntOrDefault(moisField.getText(), 0));
        objectif.setCommentaire(commentaireField.getText());
        objectif.setCatobj(catobjs.get(0));


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
            objectifService.update(objectif);
            System.out.println("Objectif mis à jour avec succès !");

            if (onSaveCallback != null) {
                onSaveCallback.run();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterObjectif.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


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

            String categoryName = objectif.getCatobj().getCatobj();


            typecat.getSelectionModel().select(categoryName);
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

//    public void sendSMS(String to,String body) {
//        Twilio.init("AC3788dff27b6661da6ca889608c31c6ad", "d21bc4cfe5a45eace407599c14e1b920");
//        Message message = Message.creator(
//                        new PhoneNumber("+21654629015"),
//                        new PhoneNumber("+16184089413"),
//
//                        body)
//                .create();
//        System.out.println("phoneNumberDONE");
//
//    }
//    public void checkProgressAndSendSMS(Objectif objectif, double progressPercentage, String phoneNumber) {
//        String category = objectif.getCatobj().getCatobj();
//        double targetPercentage = 0;
//
//        switch (category) {
//            case "immobilier":
//            case "véhicule":
//                targetPercentage = 10;
//                break;
//            case "voyage":
//                targetPercentage = 25;
//                break;
//            default:
//                if (objectif.getMontant_globale() > 20000) {
//                    targetPercentage = 10;
//                } else {
//                    targetPercentage = 25;
//                }
//                break;
//        }
//
//        double step = targetPercentage / 100.0;
//        double totalAmount = objectif.getMontant_globale();
//        double currentAmount = objectif.getMontant_conserve();
//
//        while (currentAmount >= step * totalAmount) {
//            sendSMS(phoneNumber, "Félicitations ! Vous avez atteint " + progressPercentage + "% de votre objectif \"" + objectif.getTitre() + "\".");
//            step += targetPercentage / 100.0;
//        }
//    }
//
//
//    public void updateMontantConserve(Objectif objectif, double nouveauMontant, String phoneNumber) {
//        objectif.setMontant_conserve(objectif.getMontant_conserve() + nouveauMontant);
//        objectifService.update(objectif);
//
//        double progressPercentage = (objectif.getMontant_conserve() / objectif.getMontant_globale()) * 100;
//        checkProgressAndSendSMS(objectif, progressPercentage, phoneNumber);
//    }

}