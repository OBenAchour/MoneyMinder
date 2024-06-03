package MoneyMinder;

import Models.Assets;
import Models.CatAssets;
import Models.User;
import Services.ServiceCatAssets;
import Services.Serviceassets;
import Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       Connection cx = MyConnexion.getInstance().getCnx();


        // Création de l'utilisateur
        User use1 = new User(1, "mannaa", "hsan", "azerty1", new Date(1996, 2, 6), "hsan.tn");

        // Création de la catégorie d'assets
        CatAssets catL = new CatAssets(3, "Formation", use1);
        CatAssets catC = new CatAssets(4, "certificat", use1);

        // Création des assets
        Assets asset1 = new Assets(1, "Java", 9.99f, catL);
        Assets asset2 = new Assets(2, "base des donees", 15.00f, catL);
        Assets asset3 = new Assets(3, "stqb", 1500.00f, catC);
        Assets asset4 = new Assets(19, "shaima", 100f, catC);
        // Création des services
        Serviceassets sa = new Serviceassets();
        ServiceCatAssets ca = new ServiceCatAssets();

        // Exemple d'ajout d'assets et catassets
        // Supposons que ces services implémentent des méthodes pour gérer les entités
      //  sa.add(asset1);
        //  sa.add(asset2);
       // ca.add(catL);
        //ca.add(catC);
       // sa.add(asset3);
        //sa.add(asset1);
        //sa.delete(asset1);
        sa.delete(asset4);
       // System.out.println(sa.getAll());
        System.out.println(sa.getbyfilter("titre='stqb'"));
    }


}
