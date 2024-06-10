package Services.GestionTransactionsServices;

import Interfaces.InterfaceMoneyMinder;
import Models.Catrev;
import Models.Frequence;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeService implements InterfaceMoneyMinder<Frequence> {
    //var
    Connection cnx= Myconnection.getInstance().getCnx();
    @Override
        public void add(Frequence frequence) {
            String req = "INSERT INTO `transactiontype`(`type`) VALUES (?)";
            try{
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1,frequence.getFrequence());
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
               }

    @Override
    public void delete(Frequence frequence) {
        String req = "DELETE FROM `transactiontype` WHERE id=?";
        try{
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,frequence.getId());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Frequence frequence) {
     String req ="UPDATE `transactiontype` SET `type`=? WHERE id";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,frequence.getFrequence());
            ps.setInt(1, frequence.getId());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Frequence> getAll() {
        List<Frequence> frequences = new ArrayList<>();
        String req = "SELECT * FROM `transactiontype`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Frequence frequence = new Frequence();
                frequence.setId(res.getInt("id fréquence"));
                frequence.setFrequence(res.getString("type"));
                frequences.add(frequence);
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);}

        return frequences;
    }

    @Override
    public List<Frequence> getbyfilter(String filter) {
        List<Frequence> frequences = new ArrayList<>();
        String req = "SELECT * FROM `transactiontype`"+filter;
        try {Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Frequence frequence = new Frequence();
                frequence.setId(res.getInt("id fréquence"));
                frequence.setFrequence(res.getString("type"));
                frequences.add(frequence);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return frequences;
    }

    @Override
    public List<Frequence> getbyid(int id) {
        List<Frequence> frequences = new ArrayList<>();
        String req = "SELECT * FROM `transactiontype` WHERE id=" + "`" + id + "`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Frequence frequence = new Frequence();
                frequence.setId(res.getInt("id fréquence"));
                frequence.setFrequence(res.getString("type"));
                frequences.add(frequence);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return frequences;
    }
}
