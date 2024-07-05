package Models;

public class CatDep {
    private int id;

    private String type;

    //constructors


    public CatDep(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public CatDep(String type) {
        this.type = type;
    }

    public CatDep() {
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //tostring


    @Override
    public String toString() {
        return "CatDep{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
