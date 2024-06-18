package Models;

public class Catobj {
    int idObj;
    String catobj;


    public Catobj() {}

    public Catobj(int id_obj, String catobj) {
        this.idObj = id_obj;
        this.catobj = catobj;
    }

    public Catobj(String catobj) {

        this.catobj = catobj;
    }

    public int getId_obj() {
        return idObj;
    }

    public void setId_obj(int id_obj) {
        this.idObj = id_obj;
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
                "id_obj=" + idObj +
                ", catobj='" + catobj + '\'' +
                '}';
    }
}
