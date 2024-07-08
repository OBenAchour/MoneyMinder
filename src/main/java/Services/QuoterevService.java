package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Quotedep;
import Models.Quoterev;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuoterevService implements InterfaceMoneyMinder<Quoterev> {
    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    @Override
    public void add(Quoterev quoterev) {
        String req = "INSERT INTO `quote_rev`(`quote`) VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, quoterev.getQuote());
            ps.executeUpdate();
            System.out.println("quote Revenue ajoutée avec succés");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
public void delete(Quoterev quoterev) {
    String req = "DELETE FROM `quote_rev` WHERE `id_quote_rev`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, quoterev.getId());
        ps.executeUpdate();
        System.out.println("Quote Revenue supprimée avec succés");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

@Override
public void update(Quoterev quoterev) {
    String req = "UPDATE `quote_rev` SET `quote`=? WHERE `id_quote_rev`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, quoterev.getQuote());
        ps.setInt(2, quoterev.getId());
        ps.executeUpdate();
        System.out.println("Quote Revenue modifiée avec succés");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public List<Quoterev> getAll() {
        List<Quoterev> quoterevs = new ArrayList<>();
        String req ="SELECT * FROM `quote_rev`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quoterev quoterev = new Quoterev();
                quoterev.setId(res.getInt("id_quote_rev"));
                quoterev.setQuote(res.getString("quote"));
                quoterevs.add(quoterev);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quoterevs;
    }

    @Override
    public List<Quoterev> getbyfilter(String filter) {
        List<Quoterev> quoterevs = new ArrayList<>();
        String req ="SELECT * FROM `quote_rev`"+filter;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quoterev quoterev = new Quoterev();
                quoterev.setId(res.getInt("id_quote_rev"));
                quoterev.setQuote(res.getString("quote"));
                quoterevs.add(quoterev);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quoterevs;
    }

    @Override
    public List<Quoterev> getbyid(int id) {
        List<Quoterev> quoterevs = new ArrayList<>();
        String req="SELECT * FROM `quote_rev`WHERE id_quote_rev="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quoterev quoterev = new Quoterev();
                quoterev.setId(res.getInt("id_quote_rev"));
                quoterev.setQuote(res.getString("quote"));
                quoterevs.add(quoterev);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quoterevs;
    }
}
