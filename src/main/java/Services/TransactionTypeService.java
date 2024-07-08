package Services.GestionTransactionsServices;

import Interfaces.InterfaceMoneyMinder;
import Models.Catrev;
import Models.Frequence;
import Models.Transactiontype;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeService implements InterfaceMoneyMinder<Transactiontype> {
    //var
    Connection cnx= Myconnection.getInstance().getCnx();
    @Override
        public void add(Transactiontype transaction) {
            String req = "INSERT INTO `transactiontype`(`type`) VALUES (?)";
            try{
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1,transaction.getType());
                ps.executeUpdate();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
               }

    @Override
    public void delete(Transactiontype transaction) {
        String req = "DELETE FROM `transactiontype` WHERE id=?";
        try{
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,transaction.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Transactiontype transaction) {
     String req ="UPDATE `transactiontype` SET `type`=? WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,transaction.getType());
            ps.setInt(2, transaction.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transactiontype> getAll() {
        List<Transactiontype> transactions = new ArrayList<>();
        String req = "SELECT * FROM `transactiontype`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Transactiontype transaction = new Transactiontype();
                transaction.setId(res.getInt("id"));
                transaction.setType(res.getString("type"));
                transactions.add(transaction);
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);}

        return transactions;
    }

    @Override
    public List<Transactiontype> getbyfilter(String filter) {
        List<Transactiontype> transactions = new ArrayList<>();
        String req = "SELECT * FROM `transactiontype`"+filter;
        try {Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Transactiontype transaction = new Transactiontype();
                transaction.setId(res.getInt("id"));
                transaction.setType(res.getString("type"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transactiontype> getbyid(int id) {
    List<Transactiontype> transactions = new ArrayList<>();
    String req = "SELECT * FROM `transactiontype` WHERE id=" +  id ;
    try {
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()) {
            Transactiontype transaction = new Transactiontype();
            transaction.setId(res.getInt("id"));
            transaction.setType(res.getString("type"));
            transactions.add(transaction);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return transactions;
}
}

