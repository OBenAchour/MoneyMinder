package Models;

public class CatAssets {
    private int id_cat;
    private String categ;

    public CatAssets() {
    }

    public CatAssets(String categ) {
        this.categ = categ;
    }

    public CatAssets(int id_cat, String categ) {
        this.id_cat = id_cat;
        this.categ = categ;
    }
    public CatAssets(int id_cat){
        this.id_cat=id_cat;
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

    @Override
    public String toString() {
        return "CatAssets{" +
                "id_cat=" + id_cat +
                ", categ='" + categ + '\'' +
                '}';
    }
}
