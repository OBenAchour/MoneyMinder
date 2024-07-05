package Models;

public class Errorcat {
    int error_id;
    String titre;
    Reporterror id_report;

    public Errorcat(int error_id, String titre, Reporterror id_report) {
        this.error_id = error_id;
        this.titre = titre;
        this.id_report = id_report;
    }

    public Errorcat(int error_id, String titre) {
        this.error_id = error_id;
        this.titre = titre;
    }

    public Errorcat() {
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Reporterror getId_report() {
        return id_report;
    }

    public void setId_report(Reporterror id_report) {
        this.id_report = id_report;
    }

    @Override
    public String toString() {
        return "Errorcat{" +
                "error_id=" + error_id +
                ", titre='" + titre + '\'' +
                ", id_report=" + id_report +
                '}';
    }
}
