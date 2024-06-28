package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection {

    private Connection cnx;
    private static Myconnection instance;

    private Myconnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/moneyminderdb";
            String username = "root";
            String password = "";
            cnx = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Myconnection getInstance() {
        if (instance == null) {
            instance = new Myconnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
