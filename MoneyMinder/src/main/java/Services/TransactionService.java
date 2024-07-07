package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Commentaire;
import Models.Sujet;
import Models.Transaction;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionService implements InterfaceMoneyMinder <Transaction> {

    //var
    Connection cnx= Myconnection.getInstance().getCnx();

    @Override
    public void add(Transaction transaction) {
        String req = "INSERT INTO `transactions`(`titre`, `montant`, `mois`, `annee`, `commentaire`, `id_user`, `id_freq`, `type`, `id_quote_dep`, `id_quote_rev`, `id_cat_depense`, `id_cat_revenu`) VALUES ('?','?','?','?','?','?','?','?','?','?','?','?')";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Transaction transaction) {

    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public List<Transaction> getbyfilter() {
        return null;
    }

    @Override
    public List<Transaction> getbyid() {
        return null;
    }

}
