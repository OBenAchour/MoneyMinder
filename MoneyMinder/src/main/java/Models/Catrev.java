package Models;

public class Catrev {
    private int id;
    private String type;

    //constructors


    public Catrev(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Catrev(String type) {
        this.type = type;
    }

    public Catrev() {
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

    // to string


    @Override
    public String toString() {
        return "Catrev{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
