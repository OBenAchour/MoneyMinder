package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Models.*;
import Models.Catrev;
import Models.Frequence;
import Models.Quotedep;
import Models.Quoterev;

import Services.FrequenceService;
import Services.QuotedepService;
import Services.QuoterevService;
import Services.TransactionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddDepenseH {

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
    private TextArea Quotedep;

    @FXML
    private TextArea TACommentaire;

    @FXML
    private Button To_depense;

    @FXML
    private Button add_depense;

    @FXML
    private TextField annee;

    @FXML
    private TextField montant;

    @FXML
    private TextField titre;

    //varmpois
    public ObservableList<Integer> moisdata = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);


    //frequence
    FrequenceService fs = new FrequenceService();
    List<Frequence> Frequences = fs.getAll();

    public ObservableList<String> Fdata = FXCollections.observableArrayList(Frequences.stream().map(f -> f.getFrequence()).toList());


    //catrev
    Catrev CR=new Catrev();
    Services.CatrevService CRS=new Services.CatrevService();
    List<Models.Catrev>listtcr=CRS.getbyfilter("where catRev=''");

    //Quoterev
    Models.Quoterev QR=new Models.Quoterev();
    QuoterevService QRS=new QuoterevService();
    List<Models.Quoterev>listtqr=QRS.getbyfilter("where quote=''");

    //Quotedep
    QuotedepService QS=new QuotedepService();
    List<Models.Quotedep> QDlist = QS.getbyfilter("where quote!=''");
    private  String Quote =QDlist.get((int)(Math.random())).getQuote();
    Models.Quotedep QD = new Models.Quotedep();


    //user
    User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");

    //transactiontype
    Transactiontype TT=new Transactiontype();
    Services.GestionTransactionsServices.TransactionTypeService TTS= new Services.GestionTransactionsServices.TransactionTypeService();

    //catdep
    Services.CatdepService cd = new Services.CatdepService();
    List<Models.CatDep> Categories = cd.getAll();
    public ObservableList<String> CDdata = FXCollections.observableArrayList(Categories.stream().map(cd->cd.getType()).toList());;
    Models.CatDep CD = new CatDep();
    Services.CatdepService CDS = new Services.CatdepService();

    //transactiontype
    List<Transactiontype>listtt=TTS.getbyfilter("where type='Dépense'");


    //Transaction
    TransactionService TS = new TransactionService();

    @FXML
    void initialize() {
        To_depense.setOnAction(event -> To_depense());
        add_depense.setOnAction(event -> add_depense());
        CB_freq.setItems(Fdata);
        CB_cat.setItems(CDdata);
        CB_mois.setItems(moisdata);
        Quotedep.setText("Important  :  "+Quote);
    }
        //commit
    private void add_depense() {
        //quoterev
        QR.setId(listtqr.get(0).getId());
        QR.setQuote(listtqr.get(0).getQuote());
        //caterev
        CR.setId(listtcr.get(0).getId());
        CR.setType(listtcr.get(0).getType());
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
        List <CatDep> clist = CDS.getbyfilter("where catDep='"+Cat+"'");
        CD.setId(clist.get(0).getId());
        CD.setType(clist.get(0).getType());
        //quotedep
        QD.setQuote(Quote);
        List <Quotedep> QDlist = QS.getbyfilter("where quote='"+Quote+"'");
        QD.setId(QDlist.get(0).getId());
        //varmontant
        Float Montant=Float.parseFloat(montant.getText());
        Transaction T=new Transaction(mois,Annee,u,commentaire,Titre,f,Montant,TT,QD,QR,CR,CD);
        System.out.println(Quote);
        System.out.println(QD);
        TS.add(T);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(" revenu ajouter avec succées !");
        alert.show();
    }

    private void To_depense() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/DepenseH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)To_depense.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


