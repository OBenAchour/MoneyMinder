package Models;

public class Sujet {
    int id_suj;
    String titre, sujet;
    User id_user;
    public Sujet(int id_sujet) {}

    // Constructeur avec paramètres
    public Sujet(int id_suj, String titre, String sujet, User id_user) {
        this.id_suj = id_suj;
        this.titre = titre;
        this.sujet = sujet;
        this.id_user = id_user;
    }

    // Getters et setters (si nécessaire)
    public int getId_suj() {
        return id_suj;
    }

    public void setId_suj(int id_suj) {
        this.id_suj = id_suj;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Sujet{" +
                "id_suj=" + id_suj +
                ", titre='" + titre + '\'' +
                ", sujet='" + sujet + '\'' +
                ", id_user=" + id_user +
                '}';
    }

}
