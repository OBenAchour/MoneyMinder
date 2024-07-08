package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Catrev;
import Models.Quoterev;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CatrevService implements InterfaceMoneyMinder<Catrev> {

    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    @Override
    public void add(Catrev catrev) {
        String req ="INSERT INTO `categorierev`(`catRev`) VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,catrev.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Catrev catrev) {
        String req ="DELETE FROM `categorierev` WHERE idCatRev = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,catrev.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Catrev catrev) {
        String req ="UPDATE `categorierev` SET `catRev`= ? WHERE idCatRev= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,catrev.getType());
            ps.setInt(2,catrev.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Catrev> getAll() {
        List<Catrev> catrevs = new ArrayList<>();
        String req ="SELECT * FROM `categorierev`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev catrev = new Catrev();
                catrev.setId(res.getInt("idCatRev"));
                catrev.setType(res.getString("catRev"));
                catrevs.add(catrev);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return catrevs;
    }

    @Override
    public List<Catrev> getbyfilter(String filter) {
        List<Catrev> catrevs = new ArrayList<>();
        String req ="SELECT * FROM `categorierev`"+filter;
        try {Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev catrev = new Catrev();
                catrev.setId(res.getInt("idCatRev"));
                catrev.setType(res.getString("catRev"));
                catrevs.add(catrev);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catrevs;
    }

    @Override
    public List<Catrev> getbyid(int id) {
        List<Catrev> catrevs = new ArrayList<>();
        String req="SELECT * FROM `categorierev`WHERE idCatRev="+id;
        try {Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev catrev = new Catrev();
                catrev.setId(res.getInt("idCatRev"));
                catrev.setType(res.getString("catRev"));
                catrevs.add(catrev);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catrevs;
    }
}
