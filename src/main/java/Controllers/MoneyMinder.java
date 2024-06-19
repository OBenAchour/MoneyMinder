package Controllers;

import Interfaces.InterfaceMoneyMinder;
import Models.*;
import Models.Frequence;
import Services.GestionTransactionsServices.*;
import Utils.Myconnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

public class MoneyMinder extends Application {

    public static void main(String[] args) {
        launch(args);

//        Connection cnx= Myconnection.getInstance().getCnx();

        //test CRUD categorie depense
//        CatDep Salaire =new CatDep("Salaire");
//        CatDep devidende =new CatDep("devidendee");
//        CatDep Salaireupdate =new CatDep(2,"Salairee");
//        InterfaceMoneyMinder<CatDep> cds=new CatdepService();
        //cds.add(devidende);
        //cds.add(Salaire);
        //cds.update(Salaireupdate);
        //cds.delete(Salaireupdate);
        //System.out.println(cds.getbyfilter("where catDep='devidendee'"));
        //System.out.println(cds.getAll());
        //System.out.println(cds.getbyid(1));

        //test CRUD frequence
//        Models.Frequence Journalier=new Models.Frequence(1,"Journalier");
//        Models.Frequence Journalierupdate=new Models.Frequence(1,"Journalierr");
//        InterfaceMoneyMinder<Frequence> fs=new FrequenceService();
        //fs.add(Journalier);
        //fs.delete(Journalierupdate);
        //fs.update(Journalierupdate);
        //System.out.println(fs.getAll());
        //System.out.println(fs.getbyfilter("where type='Journalierr'"));
        //System.out.println(fs.getbyid(1));

        // test CRUD transaction type
//        Transactiontype tt=new Transactiontype(2,"Dépense");
//        Transactiontype ttupdate =new Transactiontype(2,"Revenuee");
//        TransactionTypeService tts=new TransactionTypeService();
        // tts.add(tt);
        // tts.add(ttupdate);
        //tts.update(ttupdate);
        //tts.delete(ttupdate);
        //System.out.println(tts.getbyid(2));
        //System.out.println(tts.getAll());
        //System.out.println(tts.getbyfilter("where type='Dépense'"));

        //test CRUD Catrev
//        Catrev cr =new Catrev(2,"revenu");
//        Catrev cr1 =new Catrev("aaa");
//        Catrev cru =new Catrev(1,"revenuuuu");
//        CatrevService crs=new CatrevService();
        //crs.add(cr1);
        //crs.add(cr);
        //crs.update(cru);
        // crs.delete(cru);
        //System.out.println(crs.getbyid(2));
        //System.out.println(crs.getAll());
        //System.out.println(crs.getbyfilter("where catRev='aaa'"));

        //test CRUD quoterev
//        Quoterev qr =new Quoterev(3,"gain more spend less");
//        Quoterev qr1 =new Quoterev(2,"spending is an  addiction that you gain when you stop doing it ");
//        QuoterevService qrs =new QuoterevService();
        //qrs.add(qr);
        // qrs.add(qr1);
        //qrs.delete(qr1);
        //qrs.update(qr1);
        //System.out.println(qrs.getbyid(2));
        //System.out.println(qrs.getAll());
        //System.out.println(qrs.getbyfilter("where quote='spending is an  addiction that you gain when you stop doing it $$'"));

        //test CRUD quotedep
//        Quotedep qd =new Quotedep(1," spend less gain more");
//        Quotedep qd1 =new Quotedep("make sure to spend your money wisely");
//        QuotedepService qds =new QuotedepService();
        //qds.add(qd);
        //qds.delete(qd1);
        //qds.update(qd1);
//      System.out.println(qds.getbyfilter("where quote=' spend less gain more'"));
//      System.out.println(qds.getbyid(4));
//      System.out.println(qds.getAll());

      //test CRUD Transaction
//        User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");
//        Transaction tt1=new Transaction(3,4,2024,u,"transaction 2 ","",Journalier,2000F,tt,qd,qr,cr,devidende);
//        TransactionService ts=new TransactionService();
//        ts.add(tt1);
        //ts.update(tt1);
        //ts.delete(tt1);
        //System.out.println(ts.getAll());



    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/HomeAdmin.fxml"));
        try {
            Parent root = Loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}