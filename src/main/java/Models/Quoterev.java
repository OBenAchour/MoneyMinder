package Models;

import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quoterev {

    private int id;
    private String quote;

    //var

    Connection cnx= Myconnection.getInstance().getCnx();

    //constructors


    public Quoterev(int id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Quoterev(String quote) {
        this.quote = quote;
    }

    public Quoterev() {
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

    // get Quote rev by id

    public Quoterev getQuoteRevbyid(int id)  throws SQLException {
        String req ="SELECT * FROM `quote_rev` WHERE id_quote_rev=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Quoterev qr=new Quoterev();
                qr.setId(res.getInt("id_quote_rev"));
                qr.setQuote(res.getString("quote"));
                return qr;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    // to string


    @Override
    public String toString() {
        return "Quoterev{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
