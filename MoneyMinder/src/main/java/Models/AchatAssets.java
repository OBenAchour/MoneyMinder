package Models;

import java.util.Date;

public class AchatAssets extends Assets {
    private int id_achat;
    private String titre;
    private float prix;
    private Date date_achat; // Propriété date_achat

    public AchatAssets() {
    }

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
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

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    // Vous pouvez ajouter d'autres méthodes si nécessaire

    @Override
    public String toString() {
        return "AchatAssets{" +
                "id_achat=" + id_achat +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", date_achat=" + date_achat +
                '}';
    }
}
