package Models;

public class Commentaire {
    int id_comm;
    String cmt;
    Sujet id_sujet;
    public Commentaire(int id_cmt, String cmt, int id_sujet) {}
    public Commentaire(int id_comm, String cmt, Sujet id_sujet) {
        this.id_comm = id_comm;
        this.cmt = cmt;
        this.id_sujet = id_sujet;
    }

    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public Sujet getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(Sujet id_sujet) {
        this.id_sujet = id_sujet;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id_comm=" + id_comm +
                ", cmt='" + cmt + '\'' +
                ", id_sujet=" + id_sujet +
                '}';
    }


}
