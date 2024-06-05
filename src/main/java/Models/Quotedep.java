package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quotedep {
    private int id;
    private String quote;

    //var

    Connection cnx= Myconnection.getInstance().getCnx();

    //contructors


    public Quotedep(int id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Quotedep(String quote) {
        this.quote = quote;
    }

    public Quotedep() {
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    //get quotedep by id

    public  Quotedep getQuotedepById(int id) {
        String req="SELECT * FROM `quote_dep` WHERE id_quote_dep=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Quotedep quotedep = new Quotedep();
                quotedep.setId(res.getInt("id_quote_dep"));
                quotedep.setQuote(res.getString("quote"));
                return quotedep;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    //to string

    @Override
    public String toString() {
        return "Quotedep{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
