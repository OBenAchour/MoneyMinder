package Models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Portefeuille_actions {
    private int id_portefeuille;
    private Date date_creation;
    private float solde_inves;
    private int id_user;
    private List<Action> actions;
    private float total;
    private int quantite;
    private String nom;
    private float cours;
    private String type;

    public float getTotal() {
        return total;
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

    public Portefeuille_actions(float total, int quantite, String nom, float cours) {
        this.total = total;
        this.quantite = quantite;
        this.nom = nom;
        this.cours = cours;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    public Portefeuille_actions(int id_portefeuille, Date date_creation, float solde_inves, int id_user, List<Action> actions, float total) {
        this.id_portefeuille = id_portefeuille;
        this.date_creation = date_creation;
        this.solde_inves = solde_inves;
        this.id_user = id_user;
        this.actions = actions;
        this.total = total;
    }

    public Portefeuille_actions() {
        this.actions = new ArrayList<>();
    }


    public Portefeuille_actions(int id_portefeuille, Date date_creation, float solde_inves, int id_user) {
        this.id_portefeuille = id_portefeuille;
        this.date_creation = date_creation;
        this.solde_inves = solde_inves;
        this.id_user = id_user;
        this.actions = new ArrayList<>();
    }



    public int getId_portefeuille() {
        return id_portefeuille;
    }

    public void setId_portefeuille(int id_portefeuille) {
        this.id_portefeuille = id_portefeuille;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public float getSolde_inves() {
        return solde_inves;
    }

    public void setSolde_inves(float solde_inves) {
        this.solde_inves = solde_inves;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    // Méthode pour ajouter une action au portefeuille
    public void ajouterAction(Action action) {
        this.actions.add(action);
    }

    // Méthode pour supprimer une action du portefeuille
    public void supprimerAction(Action action) {
        this.actions.remove(action);
    }

    // Méthode pour obtenir la valeur totale des actions dans le portefeuille
    public float getValeurTotale() {
        float valeurTotale = 0;
        for (Action action : actions) {
            valeurTotale += action.getPrix_vente() * action.getQuantite();
        }
        return valeurTotale;
    }

    // Méthode pour acheter des actions
    public String acheterAction(Action action, int quantite) {
        float coutTotal = action.getPrix_achat() * quantite;
        if (solde_inves >= coutTotal) {
            action.setQuantite(quantite);
            this.ajouterAction(action);
            this.solde_inves -= coutTotal;
            return "Achat réussi. Actions ajoutées au portefeuille.";
        } else {
            return "Fonds insuffisants pour effectuer cet achat.";
        }
    }

    // Méthode pour vendre des actions
    public String vendreAction(Action action, int quantite) {
        if (this.actions.contains(action) && action.getQuantite() >= quantite) {
            action.setQuantite(action.getQuantite() - quantite);
            if (action.getQuantite() == 0) {
                this.supprimerAction(action);
            }
            this.solde_inves += action.getPrix_vente() * quantite;
            return "Vente réussie. Actions vendues.";
        } else {
            return "Quantité insuffisante pour effectuer cette vente.";
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

