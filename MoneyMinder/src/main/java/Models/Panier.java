package Models;

import java.util.Objects;

public class Panier extends Assets {
    private int idAssets;
    private String titre;
    private Float prix;

    public Panier() {
    }

    public Panier(int idAssets, String titre, Float prix) {
        this.idAssets = idAssets;
        this.titre = titre;
        this.prix = prix;
    }

    public int getIdAssets() {
        return idAssets;
    }

    public void setIdAssets(int idAssets) {
        this.idAssets = idAssets;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier panier)) return false;
        return idAssets == panier.idAssets && Objects.equals(titre, panier.titre) && Objects.equals(prix, panier.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssets, titre, prix);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "idAssets=" + idAssets +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                '}';
    }
}