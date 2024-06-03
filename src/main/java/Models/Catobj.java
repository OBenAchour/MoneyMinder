package Models;

public class Catobj {
    int id_obj;
    String catobj;


    public Catobj() {}

    public Catobj(int id_obj, String catobj) {
        this.id_obj = id_obj;
        this.catobj = catobj;
    }

    public Catobj(String catobj) {
        this.catobj = catobj;
    }

    public int getId_obj() {
        return id_obj;
    }

    public void setId_obj(int id_obj) {
        this.id_obj = id_obj;
    }

    public String getCatobj() {
        return catobj;
    }

    public void setCatobj(String catobj) {
        if (catobj == "Voyage" || catobj == "Véhicule" || catobj == "Immobilier" || catobj == "autre")
        {
            this.catobj = catobj;
        }
    }

    @Override
    public String toString() {
        return "Catobj{" +
                "id_obj=" + id_obj +
                ", catobj='" + catobj + '\'' +
                '}';
    }
}
