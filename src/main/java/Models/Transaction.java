package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class Transaction {
    int id_trans,mois,annee;
    User id_user;
    String commentaire, titre;
    Frequence id_freq;
    Float montant;
    private Transactiontype Type;
    private Quotedep quotedep;
    private Quoterev quoterev;
    private Catrev catrev;
    private CatDep catDep;





    //Constructeurs


    public Transaction(int id_trans, int mois, int annee, User id_user, String commentaire, String titre, Frequence id_freq, Float montant, Transactiontype type, Quotedep quotedep, Quoterev quoterev, Catrev catrev, CatDep catDep) {
        this.id_trans = id_trans;
        this.mois = mois;
        this.annee = annee;
        this.id_user = id_user;
        this.commentaire = commentaire;
        this.titre = titre;
        this.id_freq = id_freq;
        this.montant = montant;
        Type = type;
        this.quotedep = quotedep;
        this.quoterev = quoterev;
        this.catrev = catrev;
        this.catDep = catDep;
    }

    public Transaction(int mois, int annee, User id_user, String commentaire, String titre, Frequence id_freq, Float montant, Transactiontype type, Quotedep quotedep, Quoterev quoterev, Catrev catrev, CatDep catDep) {
        this.mois = mois;
        this.annee = annee;
        this.id_user = id_user;
        this.commentaire = commentaire;
        this.titre = titre;
        this.id_freq = id_freq;
        this.montant = montant;
        Type = type;
        this.quotedep = quotedep;
        this.quoterev = quoterev;
        this.catrev = catrev;
        this.catDep = catDep;
    }

    public Transaction() {
    }

    // Setters and Getters


    public int getId_trans() {
        return id_trans;
    }

    public void setId_trans(int id_trans) {
        this.id_trans = id_trans;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Frequence getId_freq() {
        return id_freq;
    }

    public void setId_freq(Frequence id_freq) {
        this.id_freq = id_freq;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Transactiontype getType() {
        return Type;
    }

    public void setType(Transactiontype type) {
        Type = type;
    }

    public Quotedep getQuotedep() {
        return quotedep;
    }

    public void setQuotedep(Quotedep quotedep) {
        this.quotedep = quotedep;
    }

    public Quoterev getQuoterev() {
        return quoterev;
    }

    public void setQuoterev(Quoterev quoterev) {
        this.quoterev = quoterev;
    }

    public Catrev getCatrev() {
        return catrev;
    }

    public void setCatrev(Catrev catrev) {
        this.catrev = catrev;
    }

    public CatDep getCatDep() {
        return catDep;
    }

    public void setCatDep(CatDep catDep) {
        this.catDep = catDep;
    }




    // to string


    @Override
    public String toString() {
        return "Transaction{" +
                "id_trans=" + id_trans +
                ", mois=" + mois +
                ", annee=" + annee +
                ", id_user=" + id_user +
                ", commentaire='" + commentaire + '\'' +
                ", titre='" + titre + '\'' +
                ", id_freq=" + id_freq +
                ", montant=" + montant +
                ", Type=" + Type +
                ", quotedep=" + quotedep +
                ", quoterev=" + quoterev +
                ", catrev=" + catrev +
                ", catDep=" + catDep +
                '}';
    }
}
