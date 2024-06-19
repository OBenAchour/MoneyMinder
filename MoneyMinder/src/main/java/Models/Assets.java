package Models;

import java.util.Objects;

public class Assets {
    int id_assets;
    String titre;
    Float prix;
    CatAssets id_catassets;
    User id_user;
    String statut;  // Ajout du champ statut

    public Assets() {
    }

    public Assets(int id_assets, String titre, Float prix, CatAssets id_catassets, User id_user, String statut) {
        this.id_assets = id_assets;
        this.titre = titre;
        this.prix = prix;
        this.id_catassets = id_catassets;
        this.id_user = id_user;
        this.statut = statut;  // Initialisation du champ statut
    }

    // Getters et setters pour tous les champs, y compris le nouveau champ statut

    public int getId_assets() {
        return id_assets;
    }

    public void setId_assets(int id_assets) {
        this.id_assets = id_assets;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public CatAssets getId_catassets() {
        return id_catassets;
    }

    public void setId_catassets(CatAssets id_catassets) {
        this.id_catassets = id_catassets;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "id_assets=" + id_assets +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", id_catassets=" + id_catassets +
                ", id_user=" + id_user +
                ", statut='" + statut + '\'' +  // Ajout du champ statut dans toString
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assets assets)) return false;
        return id_assets == assets.id_assets && Objects.equals(titre, assets.titre) && Objects.equals(prix, assets.prix) && Objects.equals(id_catassets, assets.id_catassets) && Objects.equals(id_user, assets.id_user) && Objects.equals(statut, assets.statut);  // Vérification du champ statut
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_assets, titre, prix, id_catassets, id_user, statut);  // Ajout du champ statut dans hashCode
    }
}
