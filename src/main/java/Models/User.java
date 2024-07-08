package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int id,tel;
    private String nom, prenom, mot_de_passe, mail;
    private Date date_de_naiss;


    public User() {}

    public User(int id, String nom, String prenom, String mot_de_passe, String mail, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;

        this.mail = mail;
        this.tel = tel;
    }
//      u = new User(id, nom, prenom, mot_de_passe, new java.util.Date(date_de_naiss.getTime()), mail);
    public User(int id,String nom,String prenom, String mot_de_passe,  Date date,String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.mail = mail;
        this.tel = tel;
        this.date_de_naiss = date;
    }
    // constructeur sans ID
    public User(int tel, String nom, String prenom, String mot_de_passe, String mail) {
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.mail = mail;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public java.sql.Date getDate_de_naiss() {
        return (java.sql.Date) date_de_naiss;
    }

    public void setDate_de_naiss(Date date_de_naiss) {
        this.date_de_naiss = date_de_naiss;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", tel=" + tel +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", mail='" + mail + '\'' +
                ", date_de_naiss=" + date_de_naiss +
                '}';
    }
}

