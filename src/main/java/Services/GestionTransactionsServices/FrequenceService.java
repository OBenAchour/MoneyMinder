package Services.GestionTransactionsServices;

import Interfaces.InterfaceMoneyMinder;
import Models.Catrev;
import Models.Frequence;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrequenceService implements InterfaceMoneyMinder<Frequence> {

    //var
    Connection cnx= Myconnection.getInstance().getCnx();
    @Override
        public void add(Frequence frequence) {
            String req="INSERT INTO `frequence`(`type`) VALUES (?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1,frequence.getFrequence());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
        public void delete(Frequence frequence) {
            String req ="DELETE FROM `frequence` WHERE id_freq= ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1,frequence.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    @Override
        public void update(Frequence frequence) {
            String req = "UPDATE `frequence` SET `type`=? WHERE `id_freq`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, frequence.getFrequence());
                ps.setInt(2, frequence.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
        public List<Frequence> getAll() {
            List<Frequence> frequences = new ArrayList<>();
            String req ="SELECT * FROM `frequence`";
            try {
                Statement st = cnx.createStatement();
                ResultSet res = st.executeQuery(req);
                while (res.next()){
                    Frequence frequence = new Frequence();
                    frequence.setId(res.getInt("id_freq"));
                    frequence.setFrequence(res.getString("frequence"));
                    frequences.add(frequence);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return frequences;
        }

    @Override
    public List<Frequence> getbyfilter(String filter) {
        List<Frequence> frequences = new ArrayList<>();
        String req="SELECT * FROM `frequence`"+filter;
        try {Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Frequence frequence= new Frequence();
                frequence.setId(res.getInt("id_freq"));
                frequence.setFrequence(res.getString("frequence"));
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
            String req="SELECT * FROM `frequence` WHERE id_freq"+"`"+id+"`";
            try {Statement st = cnx.createStatement();
                ResultSet res = st.executeQuery(req);
                while (res.next()){
                    Frequence frequence= new Frequence();
                    frequence.setId(res.getInt("id_freq"));
                    frequence.setFrequence(res.getString("frequence"));
                    frequences.add(frequence);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return frequences;
        }
}
