package Models;

public class CatAssets {
    int id_cat;
    String categ;
    User id_user;

    public CatAssets() {
    }

    public CatAssets(int id_cat, String categ, User id_user) {
        this.id_cat = id_cat;
        this.categ = categ;
        this.id_user = id_user;
    }

    public CatAssets(int idCatassets) {
       this.id_cat = idCatassets;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "CatAssets{" +
                "id_cat=" + id_cat +
                ", categ='" + categ + '\'' +
                ", id_user=" + id_user +
                '}';
    }
}
