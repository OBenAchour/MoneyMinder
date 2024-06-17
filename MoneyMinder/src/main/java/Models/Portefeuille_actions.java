package Models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Portefeuille_actions {
    private int id_portefeuille;
    private Date date_creation;
    private float solde_inves;
    private User id_user;
    private List<Action> actions;


    public Portefeuille_actions() {
        this.actions = new ArrayList<>();
    }


    public Portefeuille_actions(int id_portefeuille, Date date_creation, float solde_inves, User id_user) {
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

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
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
}

