package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Frequence {
private int id;
private String frequence;

//var

    Connection cnx= Myconnection.getInstance().getCnx();


//Constructors


    public Frequence(int id, String frequence) {
        this.id = id;
        this.frequence = frequence;
    }

    public Frequence(String frequence) {
        this.frequence = frequence;
    }

    public Frequence() {
    }

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }


    //get frequence by id

    public Frequence getFrequenceById(int id) throws SQLException {
        String req = "SELECT * FROM frequence    WHERE id_freq = '?' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Frequence f=new Frequence();
                f.id = rs.getInt("id_freq");
                f.frequence = rs.getString("frequence");
                return f;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    //tostring


    @Override
    public String toString() {
        return "Frequence{" +
                "id=" + id +
                ", frequence='" + frequence + '\'' +
                '}';
    }
}
