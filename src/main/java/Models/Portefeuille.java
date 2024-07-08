package Models;

public class Portefeuille {
    private int id;
    private int userId;
    private String nom;
    private float cours;
    private int quantite;
    private float total;

    public Portefeuille() {
    }

    public Portefeuille(int id, int userId, String nom, float cours, int quantite, float total) {
        this.id = id;
        this.userId = userId;
        this.nom = nom;
        this.cours = cours;
        this.quantite = quantite;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
