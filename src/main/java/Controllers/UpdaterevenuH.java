package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Models.Catrev;
import Models.Frequence;
import Models.Quoterev;
import Models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UpdaterevenuH implements Initializable {

    private Transaction VT;

    public void setVT(Transaction VT) {
        this.VT = VT;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> CB_cat;

    @FXML
    private ComboBox<String> CB_freq;

    @FXML
    private ComboBox<Integer> CB_mois;

    @FXML
    private TextArea TACommentaire;

    @FXML
    private Button To_revenu;

    @FXML
    private Button update_revenu;

    @FXML
    private TextField annee;

    @FXML
    private TextField montant;

    @FXML
    private TextField titre;

    //frequence
    Services.FrequenceService fs = new Services.FrequenceService();
    List<Frequence> Frequences = fs.getAll();
    public ObservableList<String> Fdata = FXCollections.observableArrayList(Frequences.stream().map(f -> f.getFrequence()).toList());

    //Categprie

    Services.CatrevService cr = new Services.CatrevService();
    List<Models.Catrev> Categories = cr.getAll();
    public ObservableList<String> CRdata = FXCollections.observableArrayList(Categories.stream().map(CR->CR.getType()).toList());
    Catrev CR = new Catrev();
    Services.CatrevService CRS = new Services.CatrevService();

    //var mois
    public ObservableList<Integer> moisdata = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);




    private void update_revenu() {
        System.out.println(VT);
        //varmois
        Integer mois = CB_mois.getValue();
        //varanne
        Integer Annee =Integer.parseInt(annee.getText()) ;
        //varcommentaire
        String commentaire = TACommentaire.getText();
        //vartitre
        String Titre = titre.getText();
        //varfréquence
        String freq = CB_freq.getValue();
        List <Frequence> flist = fs.getbyfilter("where type='"+freq+"'");
        Frequence f=new Frequence();
        f.setId(flist.get(0).getId());
        f.setFrequence(flist.get(0).getFrequence());
        //varcatégorie
        String Cat = CB_cat.getValue();
        List <Catrev> clist = CRS.getbyfilter("where catRev='"+Cat+"'");
        CR.setId(clist.get(0).getId());
        CR.setType(clist.get(0).getType());
        //varmontant
        Float Montant=Float.parseFloat(montant.getText());
        Transaction T=VT;
        T.setTitre(Titre);
        T.setMontant(Montant);
        T.setAnnee(Annee);
        T.setMois(mois);
        T.setId_freq(f);
        T.setCatrev(CR);
        T.setCommentaire(commentaire);
        Services.TransactionService TS = new Services.TransactionService();
        System.out.println(T);
        TS.update(T);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(" revenu modifier avec succées !");
        alert.show();
    }

    private void To_revenu() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/RevenuH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)To_revenu.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        To_revenu.setOnAction(event -> To_revenu());
        update_revenu.setOnAction(event -> update_revenu());
        CB_freq.setItems(Fdata);
        CB_cat.setItems(CRdata);
        CB_mois.setItems(moisdata);
        if (VT!=null){
            titre.setText(VT.getTitre());
            montant.setText(String.valueOf(VT.getMontant()));
            annee.setText(String.valueOf(VT.getAnnee()));
            TACommentaire.setText(VT.getCommentaire());}
            CB_cat.setValue(VT.getCatrev().getType());
            CB_freq.setValue(VT.getId_freq().getFrequence());
            CB_mois.setValue(VT.getMois());
    }
}
