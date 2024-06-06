package Services.GestionTransactionsServices;

import Interfaces.InterfaceMoneyMinder;
import Models.Quotedep;
import Models.Transaction;
import Utils.Myconnection;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuotedepService implements InterfaceMoneyMinder<Quotedep> {
    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    @Override
public void add(Quotedep quotedep) {
           String req = "INSERT INTO `quote_dep`(`quote`) VALUES (?)";
           try {
                  PreparedStatement ps = cnx.prepareStatement(req);
                 ps.setString(1, quotedep.getQuote());
                 ps.executeUpdate();
                 System.out.println("quote Dépense ajoutée avec succés");
                } catch (SQLException e) {
                   throw new RuntimeException(e);
             }
        }

   @Override
public void delete(Quotedep quotedep) {
    String req = "DELETE FROM `quote_dep` WHERE `id_quote_dep` =?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, quotedep.getId());
        ps.executeUpdate();
        System.out.println("Quote Dépense supprimée avec succés");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

@Override
public void update(Quotedep quotedep) {
    String req = "UPDATE `quote_dep` SET `quote` =? WHERE `id_quote_dep` =?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, quotedep.getQuote());
        ps.setInt(2, quotedep.getId());
        ps.executeUpdate();
        System.out.println("Quote Dépense modifiée avec succés");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public List<Quotedep> getAll() {
        List<Quotedep> quotedeps = new ArrayList<>();
        String req ="SELECT * FROM `quote_dep`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quotedep quotedep = new Quotedep();
                quotedep.setId(res.getInt("id_quote_dep"));
                quotedep.setQuote(res.getString("quote"));
                quotedeps.add(quotedep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quotedeps;
    }

    @Override
    public List<Quotedep> getbyfilter(String filter) {
        List<Quotedep> quotedeps = new ArrayList<>();
        String req ="SELECT * FROM `quote_dep`"+filter;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quotedep quotedep = new Quotedep();
                quotedep.setId(res.getInt("id_quote_dep"));
                quotedep.setQuote(res.getString("quote"));
                quotedeps.add(quotedep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quotedeps;
    }

    @Override
    public List<Quotedep> getbyid(int id) {
        List<Quotedep> quotedeps = new ArrayList<>();
        String req="SELECT * FROM `quote_dep`WHERE id_quote_dep="+"`"+id+"`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Quotedep quotedep = new Quotedep();
                quotedep.setId(res.getInt("id_quote_dep"));
                quotedep.setQuote(res.getString("quote"));
                quotedeps.add(quotedep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quotedeps;
    }
}

