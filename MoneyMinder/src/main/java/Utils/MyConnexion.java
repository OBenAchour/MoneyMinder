package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {
    private static MyConnexion instance;
    private Connection cnx;

    private MyConnexion() {
        try {
            // Assurez-vous que les informations de connexion sont correctes
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/moneyminderdb", "root", "");
            System.out.println("Connexion établie avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static MyConnexion getInstance() {
        if (instance == null) {
            instance = new MyConnexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}