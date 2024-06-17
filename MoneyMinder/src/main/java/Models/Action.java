package Models;

public class Action {
    private int id_action;
    private String nom;
    private float cours;
    private float prix_achat;
    private float prix_vente;
    private int quantite;
    private float ouverture;
    private float cloture;
    private float prix_haut;
    private float prix_bas;
    private float variation;
    private float quantite_echange;

    public Action(int id_action, String nom, float cours, float prix_achat, float prix_vente, int quantite, float ouverture, float cloture, float prix_haut, float prix_bas, float variation, float quantite_echange) {
        this.id_action = id_action;
        this.nom = nom;
        this.cours = cours;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.quantite = quantite;
        this.ouverture = ouverture;
        this.cloture = cloture;
        this.prix_haut = prix_haut;
        this.prix_bas = prix_bas;
        this.variation = variation;
        this.quantite_echange = quantite_echange;
    }

    public Action() {}


    public Action(int id_action, float prix_achat, float prix_vente, int quantite) {
        this.id_action = id_action;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.quantite = quantite;
    }


    public int getId_action() {
        return id_action;
    }

    public void setId_action(int id_action) {
        this.id_action = id_action;
    }

    public float getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(float prix_achat) {
        this.prix_achat = prix_achat;
    }

    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getCours() {
        return cours;
    }

    public void setCours(float cours) {
        this.cours = cours;
    }

    public float getOuverture() {
        return ouverture;
    }

    public void setOuverture(float ouverture) {
        this.ouverture = ouverture;
    }

    public float getCloture() {
        return cloture;
    }

    public void setCloture(float cloture) {
        this.cloture = cloture;
    }

    public float getPrix_haut() {
        return prix_haut;
    }

    public void setPrix_haut(float prix_haut) {
        this.prix_haut = prix_haut;
    }

    public float getPrix_bas() {
        return prix_bas;
    }

    public void setPrix_bas(float prix_bas) {
        this.prix_bas = prix_bas;
    }

    public float getVariation() {
        return variation;
    }

    public void setVariation(float variation) {
        this.variation = variation;
    }

    public float getQuantite_echange() {
        return quantite_echange;
    }

    public void setQuantite_echange(float quantite_echange) {
        this.quantite_echange = quantite_echange;
    }
}
