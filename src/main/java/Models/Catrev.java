package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Catrev {
    private int id;
    private String type;

    //var

    Connection cnx= Myconnection.getInstance().getCnx();

    //constructors


    public Catrev(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Catrev(String type) {
        this.type = type;
    }

    public Catrev() {
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // get categorie rev by id

    public Catrev getCatrevbyid(int id) throws SQLException {
        String req="SELECT * FROM `categorierev` WHERE idCatRev=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Catrev catrev = new Catrev();
                catrev.setId(res.getInt("idCatRev"));
                catrev.setType(res.getString("catRev"));
                return catrev;
            }
        }
     catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return null;
    }

    // to string


    @Override
    public String toString() {
        return "Catrev{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
