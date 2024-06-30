package Controller;

import Interfaces.InterfaceMoneyMinder;
import Models.Catobj;
import Services.CatobjService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
         //CatobjService In = new CatobjService();
           //Catobj catobj = new Catobj("autre");
         // In.add(catobj);
        //System.out.println(In.getAll());
        //System.out.println(In.getbyfilter("`catobj`","Vehicule"));


        }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeAdmin.fxml"));
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
