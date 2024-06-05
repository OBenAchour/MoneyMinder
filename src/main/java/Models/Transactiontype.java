package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transactiontype {
    private int id;
    private String type;


    //var
    Connection cnx= Myconnection.getInstance().getCnx();

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


    // get type by id
    public Transactiontype gettypebyid  (int id) throws SQLException {
        String req ="SELECT * FROM `transactiontype` WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Transactiontype TP = new Transactiontype();
                TP.setId((res.getInt("id")));
                TP.setType((res.getString("type")));
                return TP;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
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
