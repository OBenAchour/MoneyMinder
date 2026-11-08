package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection {
    //database
    // Configuration from environment variables, with local-dev defaults.
    final String URL = env("DB_URL", "jdbc:mysql://localhost:3306/moneyminderdb");
    final String USR = env("DB_USER", "root");
    final String PWD = env("DB_PASSWORD", "");

    private static String env(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }

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
