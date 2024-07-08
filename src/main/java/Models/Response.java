
package Models;

public class Response {
    private int id;
    private Reclamation reclamation;
    private String message;

    private int adminId;

    public Response(int id, Reclamation reclamation, String message,int adminId) {
        this.id = id;
        this.reclamation = reclamation;
        this.message = message;
        this.adminId=adminId;
    }
    public Response(int id, Reclamation reclamation, String message) {
        this.id = id;
        this.reclamation = reclamation;
        this.message = message;
        this.adminId=adminId;
    }
    public Response(Reclamation reclamation, String message,int adminId) {
        this.reclamation = reclamation;
        this.message = message;
        this.adminId=adminId;

    }
    public Response(Reclamation reclamation, String message) {
        this.reclamation = reclamation;
        this.message = message;
        this.adminId=adminId;

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Reclamation getReclamation() { return reclamation; }
    public void setReclamation(Reclamation reclamation) { this.reclamation = reclamation; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }


    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", reclamation=" + reclamation +
                ", message='" + message + '\'' +
                '}';
    }
}


