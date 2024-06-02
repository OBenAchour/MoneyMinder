
package Models;

public class Reporterror {
    int id_rep, errorCode;
    User id_user;
    String comment ;
    String Errorcat ;

    public Reporterror(int id_rep, int errorCode, User id_user, String comment, String errorcat) {
        this.id_rep = id_rep;
        this.errorCode = errorCode;
        this.id_user = id_user;
        this.comment = comment;
        Errorcat = errorcat;
    }

    public Reporterror(int errorCode, User id_user, String comment, String errorcat) {
        this.errorCode = errorCode;
        this.id_user = id_user;
        this.comment = comment;
        Errorcat = errorcat;
    }

    public Reporterror() {
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getErrorcat() {
        return Errorcat;
    }

    public void setErrorcat(String errorcat) {
        Errorcat = errorcat;
    }

    @Override
    public String toString() {
        return "Reporterror{" +
                "id_rep=" + id_rep +
                ", errorCode=" + errorCode +
                ", id_user=" + id_user +
                ", comment='" + comment + '\'' +
                ", Errorcat='" + Errorcat + '\'' +
                '}';
    }
}




