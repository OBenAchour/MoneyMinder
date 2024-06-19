package Models;

import java.util.Date;

public class AchatAssets {
    private int id_achat;
    private Assets asset;
    private User user;
    private Date date_achat;

    public AchatAssets() {
    }

    public AchatAssets(Assets asset, User user, Date date_achat) {
        this.asset = asset;
        this.user = user;
        this.date_achat = date_achat;
    }

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    @Override
    public String toString() {
        return "AchatAssets{" +
                "id_achat=" + id_achat +
                ", asset=" + asset +
                ", user=" + user +
                ", date_achat=" + date_achat +
                '}';
    }
}
