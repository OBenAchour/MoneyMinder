package Models;

public class Status {

    private int id_status;
    private String description;
    private int id_rep;

    public Status (int id_status, String description, int id_rep) {
        this.id_status = id_status;
        this.description = description;
        this.id_rep = id_rep;
    }

    public Status(String description, int id_rep) {
        this.description = description;
        this.id_rep = id_rep;
    }

    public Status() {

    }


    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    @Override
    public String toString() {
        return "Status{" +
                "description='" + description + '\'' +
                ", id_rep=" + id_rep +
                ", id_status=" + id_status +
                '}';
    }
}
