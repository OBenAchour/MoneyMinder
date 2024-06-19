package Models;

import java.util.Date;

public class User {
    int id_user;
    String nom, prenom, mot_de_passe;
    Date date_de_naiss;
    String mail;

    public User() {
    }

    public User(int id_user) {
        this.id_user = id_user;
    }

    public User(String nom, String prenom, String mot_de_passe, Date date_de_naiss, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naiss = date_de_naiss;
        this.mail = mail;
    }

    public User(int id_user, String nom, String prenom, String mot_de_passe, Date date_de_naiss, String mail) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naiss = date_de_naiss;
        this.mail = mail;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id_user +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", date_de_naiss=" + date_de_naiss +
                ", mail='" + mail + '\'' +
                '}';
    }
}
