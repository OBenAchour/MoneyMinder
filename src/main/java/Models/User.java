package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
    int id;
    String nom, prenom, mot_de_passe;
    Date date_de_naiss;
    String mail;


    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    //constructeurs




    public User(int id, String nom, String prenom, String mot_de_passe, Date date_de_naiss, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naiss = date_de_naiss;
        this.mail = mail;
    }

    public User(String nom, String prenom, String mot_de_passe, Date date_de_naiss, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naiss = date_de_naiss;
        this.mail = mail;
    }

    public User() {
    }

    //getters and setters


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Date getDate_de_naiss() {
        return date_de_naiss;
    }

    public void setDate_de_naiss(Date date_de_naiss) {
        this.date_de_naiss = date_de_naiss;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    // get user by ID

    public User getUserbyid (int id) throws SQLException {
        String req ="SELECT * FROM `user` WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                User u =new User();
                u.setId(res.getInt("id"));
                u.setNom(res.getString("nom"));
                u.setPrenom(res.getString("prenom"));
                u.setMot_de_passe(res.getString("mot_de_passe"));
                u.setDate_de_naiss(res.getDate("date_naissance"));
                u.setMail(res.getString("mail"));
                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    //to string


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", date_de_naiss=" + date_de_naiss +
                ", mail='" + mail + '\'' +
                '}';
    }
}

