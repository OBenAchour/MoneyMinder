package Models;

public class Assets {
    int id_assets;
    String titre;
    Float prix;
    CatAssets id_catassets;
    User id_user;

    public Assets() {
    }

    public Assets(int id_assets, String titre, Float prix, CatAssets id_catassets, User id_user) {
        this.id_assets = id_assets;
        this.titre = titre;
        this.prix = prix;
        this.id_catassets = id_catassets;
        this.id_user = id_user;
    }

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

    @Override
    public String toString() {
        return "Assets{" +
                "id_assets=" + id_assets +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", id_catassets=" + id_catassets +
                ", id_user=" + id_user +
                '}';
    }
}
