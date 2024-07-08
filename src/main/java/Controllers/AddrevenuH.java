package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Models.*;
import Models.Catrev;
import Models.Frequence;
import Models.Quotedep;
import Models.Quoterev;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddrevenuH {





    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea Quoterev;

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
    private Button add_revenu;

    @FXML
    private TextField annee;

    @FXML
    private TextField montant;

    @FXML
    private TextField titre;

    //varmpois
    public ObservableList<Integer> moisdata = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);


    //frequence
    Services.FrequenceService fs = new Services.FrequenceService();
    List<Frequence> Frequences = fs.getAll();

    public ObservableList<String> Fdata = FXCollections.observableArrayList(Frequences.stream().map(f -> f.getFrequence()).toList());


    //catdep
    CatDep CD=new CatDep();
    Services.CatdepService CDS=new Services.CatdepService();
    List<CatDep>listtcd=CDS.getbyfilter("where catDep=''");

    //QuoteDep
    Quotedep QD=new Quotedep();
    Services.QuotedepService QDS=new Services.QuotedepService();
    List<Quotedep>listtqd=QDS.getbyfilter("where quote=''");

    //Quoterev
    Services.QuoterevService QS=new Services.QuoterevService();
    List<Models.Quoterev> QRlist = QS.getbyfilter("where quote!=''");
    private  String Quote =QRlist.get((int)(Math.random())).getQuote();
    Models.Quoterev QR = new Models.Quoterev();


    //user
    User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");

    //transactiontype
    Transactiontype TT=new Transactiontype();
    Services.GestionTransactionsServices.TransactionTypeService TTS= new Services.GestionTransactionsServices.TransactionTypeService();

    //catrev
    Services.CatrevService cr = new Services.CatrevService();
    List<Models.Catrev> Categories = cr.getAll();
    public ObservableList<String> CRdata = FXCollections.observableArrayList(Categories.stream().map(CR->CR.getType()).toList());
    Catrev CR = new Catrev();
    Services.CatrevService CRS = new Services.CatrevService();

    //transactiontype
    List<Transactiontype>listtt=TTS.getbyfilter("where type='Revenu'");


    //Transaction
    Services.TransactionService TS = new Services.TransactionService();


    @FXML
    void initialize() {
        To_revenu.setOnAction(event->to_revenu());
        add_revenu.setOnAction(event -> add_revenu());
        CB_freq.setItems(Fdata);
        CB_cat.setItems(CRdata);
        CB_mois.setItems(moisdata);
        Quoterev.setText("Important  :  "+Quote);
    }

    private void add_revenu() {
      //VAR
        //quotedep
        QD.setId(listtqd.get(0).getId());
        QD.setQuote(listtqd.get(0).getQuote());
        //catedep
        CD.setId(listtcd.get(0).getId());
        CD.setType(listtcd.get(0).getType());
        //trasactiotype
        TT.setId(listtt.get(0).getId());
        TT.setType(listtt.get(0).getType());
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
        //quoterev
        QR.setQuote(Quote);
        List <Quoterev> QSlist = QS.getbyfilter("where quote='"+Quote+"'");
        QR.setId(QSlist.get(0).getId());
        //varmontant
        Float Montant=Float.parseFloat(montant.getText());
        Transaction T=new Transaction(mois,Annee,u,commentaire,Titre,f,Montant,TT,QD,QR,CR,CD);
        TS.add(T);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(" revenu ajouter avec succées !");
        alert.show();
    }

    private void to_revenu() {
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

}
