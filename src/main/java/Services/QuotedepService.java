package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Quotedep;
import Utils.Myconnection;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuotedepService implements InterfaceMoneyMinder<Quotedep> {
    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    @Override
    public void add(Quotedep quotedep) {
        String req = "INSERT INTO `quote_dep`(`quote`) VALUES ('?')";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setString(1, quotedep.getQuote());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Quotedep quotedep) {

    }

    @Override
    public void update(Quotedep quotedep) {

    }

    @Override
    public List<Quotedep> getAll() {
        return null;
    }

    @Override
    public List<Quotedep> getbyfilter(String filter) {
        return null;
    }

    @Override
    public List<Quotedep> getbyid(int id) {
        return null;
    }
}
