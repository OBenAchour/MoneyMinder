package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {
    //DB
    final String URL = "jdbc:mysql://localhost:3306/moneyminderdb";
    final String USR = "root";
    final String PWD = "";

    //var
    Connection cnx;
    static MyConnexion instance;

    //Constructeur
    private MyConnexion(){
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion etablie avec succes!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnexion getInstance() {
        if(instance == null)
            instance = new MyConnexion();
        return instance;
    }
}
