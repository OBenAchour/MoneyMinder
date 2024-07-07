package Models;

import java.util.Date;

public class User {
    int id;
    String nom, prenom, mot_de_passe;
    Date date_de_naiss;
    String mail;


    //constructeurs


    public User(int id) {
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

    public java.sql.Date getDate_de_naiss() {
        return (java.sql.Date) date_de_naiss;
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

