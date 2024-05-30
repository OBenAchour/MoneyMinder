package MoneyMinder;

import Models.Assets;
import Models.CatAssets;
import Models.User;
import Serveses.Serviceassets;
import Utils.MyConnexion;

import java.sql.*;
import java.util.Date;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       Connection cx = MyConnexion.getInstance().getCnx();


        }
        User use1=new User(1,"mannaa","hsan","azerty1",new Date(1996,2,6),"hsan.tn");
        CatAssets CATL = new CatAssets(1,"livres");
        CatAssets CATM = new CatAssets(2,"musique");
        Assets Ast = new Assets(1,"livre1",3.5f,CATL,use1);

        Serviceassets sa=new Serviceassets();

    }
