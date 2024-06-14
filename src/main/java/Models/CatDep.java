package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatDep {
    private int id;

    private String type;


    //var

    Connection cnx= Myconnection.getInstance().getCnx();

    //constructors


    public CatDep(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public CatDep(String type) {
        this.type = type;
    }

    public CatDep() {
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

    //get categorie depense by id

    public  CatDep getCatDepById(int id) {
        String req="SELECT * FROM `categoriedep` WHERE idCatDep=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                CatDep catDep = new CatDep();
                catDep.setId(res.getInt("idCatDep"));
                catDep.setType(res.getString("catDep"));
                return catDep;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    //tostring


    @Override
    public String toString() {
        return "CatDep{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
