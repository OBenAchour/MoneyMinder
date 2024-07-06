package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Catobj {
    private int id_obj;
    private String catobj;

    Connection cnx= Myconnection.getInstance().getCnx();


    public Catobj(String catobj) {
        this.catobj = catobj;
    }

    public Catobj() {

    }
    // Getters and setters
    public int getId_obj() {
        return id_obj;
    }

    public void setId_obj(int id_obj) {
        this.id_obj = id_obj;
    }

    public String getCatobj() {
        return catobj;
    }

    public void setCatobj(String catobj) {
        this.catobj = catobj;
    }

    public  Catobj getCatOBJById(int id) {
        String req="SELECT * FROM `categorieobj` WHERE idObj=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Catobj catobj = new Catobj();
                catobj.setId_obj(res.getInt("idObj"));
                catobj.setCatobj(res.getString("catObj"));
                return catobj;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
