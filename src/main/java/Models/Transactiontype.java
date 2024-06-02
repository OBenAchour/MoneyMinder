package Models;

public class Transactiontype {
    private int id;
    private String type;

    //Constructors


    public Transactiontype(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Transactiontype(String type) {
        this.type = type;
    }

    public Transactiontype() {
    }

    //gettersa and setters


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

    //to string


    @Override
    public String toString() {
        return "Transactiontype{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
