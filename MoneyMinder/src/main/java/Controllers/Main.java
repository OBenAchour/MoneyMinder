package Controllers;

import Models.Assets;
import Models.CatAssets;
import Models.User;
import Services.Serviceassets;
import Utils.MyConnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }


//        Connection cx = MyConnexion.getInstance().getCnx();


        // Création de l'utilisateur
//        User use1 = new User(1, "mannaa", "hsan", "azerty1", new Date(1996, 2, 6), "hsan.tn");

        // Création de la catégorie d'assets
//        CatAssets catL = new CatAssets(3, "Formation");
        // CatAssets catC = new CatAssets( 2,"certificat");
        //CatAssets catC1 = new CatAssets( 3,"certificat");

        // Création des assets
        //Assets asset55 = new Assets( 50,"Java", 9.99f,catL,use1,"acheter");
//        Assets asset3 = new Assets(3,"base_donees", 15.00f, catL,use1,"blablabla");
        // Assets asset3 = new Assets(3, "stqb", 1500.00f, catC);
        // Assets asset4 = new Assets(19, "shaima", 100f, catC);
        // Création des services
        //Serviceassets sa = new Serviceassets();
        //ServiceCatAssets ca = new ServiceCatAssets();

        // Exemple d'ajout d'assets et catassets
        // Supposons que ces services implémentent des méthodes pour gérer les entités
        // sa.add(asset55);
        //ca.add(catL);
        //ca.add(catC);
        // sa.add(asset3);
        //sa.add(asset1);
        //sa.delete(asset1);
        //sa.delete(asset55);
        // System.out.println(sa.getAll());
        // System.out.println(sa.getbyfilter("titre='java'"));
        // System.out.println(sa.getbyid(50));
//        Serviceassets assetsService = new Serviceassets();
        //assetsService.add(asset3);

        // Récupérer un asset par ID
       /* List<Assets> assetsList = assetsService.getbyid(3); // Remplacez par l'ID de l'asset à acheter
        if (!assetsList.isEmpty()) {
            Assets assetToBuy = assetsList.get(0); // Prenez le premier asset de la liste
            assetsService.acheter(assetToBuy);
            System.out.println("Asset acheté: " + assetToBuy);
        } else {
            System.out.println("Aucun asset trouvé avec l'ID spécifié.");
        }*/

//        List<Assets> assetsToReserveList = assetsService.getbyid(2); // Remplacez par l'ID de l'asset à réserver
//        if (!assetsToReserveList.isEmpty()) {
//            Assets assetToReserve = assetsToReserveList.get(0); // Prenez le premier asset de la liste
//            assetsService.reserver(assetToReserve);
//            System.out.println("Asset réservé: " + assetToReserve);
//        } else {
//            System.out.println("Aucun asset trouvé avec l'ID spécifié.");
//        }
//
//    }
@Override
public void start(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    }

