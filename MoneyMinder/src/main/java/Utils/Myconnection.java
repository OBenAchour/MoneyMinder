package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection {
    //database
    final String URL = "jdbc:mysql://127.0.0.1:3306/moneyminder01";
    final String USR = "root";
    final String PWD = "";

    //var
    private Connection cnx;
    static Myconnection instance;

    //Constructeur
    private Myconnection(){
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

    public static Myconnection getInstance() {
        if(instance == null)
            instance = new Myconnection();
        return instance;
    }
}
