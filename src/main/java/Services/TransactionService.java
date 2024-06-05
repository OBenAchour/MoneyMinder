package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.*;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements InterfaceMoneyMinder <Transaction> {

    //var
    Connection cnx= Myconnection.getInstance().getCnx();
    @Override
    public void add(Transaction transaction) {
        String req = "INSERT INTO `transactions`(`titre`, `montant`, `mois`, `annee`, `commentaire`, `id_user`, `id_freq`, `type`, `id_quote_dep`, `id_quote_rev`, `id_cat_depense`, `id_cat_revenu`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,transaction.getTitre());
            ps.setFloat(2,transaction.getMontant());
            ps.setInt(3,transaction.getMois());
            ps.setInt(4,transaction.getAnnee());
            ps.setString(5,transaction.getCommentaire());
            ps.setInt(6,transaction.getId_user().getId());
            ps.setInt(7,transaction.getId_freq().getId());
            ps.setInt(8,transaction.getType().getId());
            ps.setInt(9,transaction.getQuotedep().getId());
            ps.setInt(10,transaction.getQuoterev().getId());
            ps.setInt(11,transaction.getCatDep().getId());
            ps.setInt(12,transaction.getCatrev().getId());
            ps.executeUpdate();
            System.out.println("transactions ajoutée avec succés");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Transaction transaction) {
        String req ="DELETE FROM `transactions` WHERE id_trans=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
         ps.setInt(1,transaction.getId_trans());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Transaction transaction) {
        String req ="UPDATE `transactions` SET `titre`=?,`montant`=?,`mois`=?,`annee`=?,`commentaire`=?,`id_user`=?,`id_freq`=?,`type`=?,`id_quote_dep`=?,`id_quote_rev`=?,`id_cat_depense`=?,`id_cat_revenu`=? WHERE 'id_trans' = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, transaction.getTitre());
            ps.setFloat(2, transaction.getMontant());
            ps.setInt(3, transaction.getMois());
            ps.setInt(4, transaction.getAnnee());
            ps.setString(5, transaction.getCommentaire());
            ps.setInt(6, transaction.getId_user().getId());
            ps.setInt(7, transaction.getId_freq().getId());
            ps.setInt(8, transaction.getType().getId());
            ps.setInt(9, transaction.getQuotedep().getId());
            ps.setInt(10, transaction.getQuoterev().getId());
            ps.setInt(11, transaction.getCatDep().getId());
            ps.setInt(12, transaction.getCatrev().getId());
            ps.setInt(13, transaction.getId_trans());
            System.out.println("transaction ùodifiée avec succées");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String req ="SELECT * FROM `transactions`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev cr=null;
                cr=cr.getCatrevbyid(res.getInt("id_cat_revenu"));
                CatDep cd=null;
                cd=cd.getCatDepById(res.getInt("id_cat_depense"));
                Quoterev qr=null;
                qr=qr.getQuoteRevbyid(res.getInt("id_quote_rev"));
                Quotedep qd =null;
                qd=qd.getQuotedepById(res.getInt("id_quote_dep"));
                Transactiontype tt=null;
                tt=tt.gettypebyid(res.getInt("idfreq"));
               User u = null;
                u=u.getUserbyid((res.getInt("id_user")));
                Frequence f = null;
                f=f.getFrequenceById((res.getInt("id_freq")));
                Transaction t =new Transaction();
                t.setId_trans(res.getInt("id_trans"));
                t.setTitre(res.getString("titre"));
                t.setMontant(res.getFloat("montant"));
                t.setMois(res.getInt("mois"));
                t.setAnnee(res.getInt("annee"));
                t.setCommentaire(res.getString("commentaire"));
                t.setId_user(u);
                t.setId_freq(f);
                t.setType(tt);
                t.setQuotedep(qd);
                t.setQuoterev(qr);
                t.setCatDep(cd);
                t.setCatrev(cr);
                transactions.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Transaction> getbyfilter(String filter) {
        List<Transaction> transactions = new ArrayList<>();
        String req ="SELECT * FROM `transactions`"+filter;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev cr=null;
                cr=cr.getCatrevbyid(res.getInt("id_cat_revenu"));
                CatDep cd=null;
                cd=cd.getCatDepById(res.getInt("id_cat_depense"));
                Quoterev qr=null;
                qr=qr.getQuoteRevbyid(res.getInt("id_quote_rev"));
                Quotedep qd =null;
                qd=qd.getQuotedepById(res.getInt("id_quote_dep"));
                Transactiontype tt=null;
                tt=tt.gettypebyid(res.getInt("idfreq"));
                User u = null;
                u=u.getUserbyid((res.getInt("id_user")));
                Frequence f = null;
                f=f.getFrequenceById((res.getInt("id_freq")));
                Transaction t =new Transaction();
                t.setId_trans(res.getInt("id_trans"));
                t.setTitre(res.getString("titre"));
                t.setMontant(res.getFloat("montant"));
                t.setMois(res.getInt("mois"));
                t.setAnnee(res.getInt("annee"));
                t.setCommentaire(res.getString("commentaire"));
                t.setId_user(u);
                t.setId_freq(f);
                t.setType(tt);
                t.setQuotedep(qd);
                t.setQuoterev(qr);
                t.setCatDep(cd);
                t.setCatrev(cr);
                transactions.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Transaction> getbyid(int id) {
        List<Transaction> transactions = new ArrayList<>();
        String req ="SELECT * FROM `transactions` WHERE id_trans="+"'"+id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Catrev cr=null;
                cr=cr.getCatrevbyid(res.getInt("id_cat_revenu"));
                CatDep cd=null;
                cd=cd.getCatDepById(res.getInt("id_cat_depense"));
                Quoterev qr=null;
                qr=qr.getQuoteRevbyid(res.getInt("id_quote_rev"));
                Quotedep qd =null;
                qd=qd.getQuotedepById(res.getInt("id_quote_dep"));
                Transactiontype tt=null;
                tt=tt.gettypebyid(res.getInt("idfreq"));
                User u = null;
                u=u.getUserbyid((res.getInt("id_user")));
                Frequence f = null;
                f=f.getFrequenceById((res.getInt("id_freq")));
                Transaction t =new Transaction();
                t.setId_trans(res.getInt("id_trans"));
                t.setTitre(res.getString("titre"));
                t.setMontant(res.getFloat("montant"));
                t.setMois(res.getInt("mois"));
                t.setAnnee(res.getInt("annee"));
                t.setCommentaire(res.getString("commentaire"));
                t.setId_user(u);
                t.setId_freq(f);
                t.setType(tt);
                t.setQuotedep(qd);
                t.setQuoterev(qr);
                t.setCatDep(cd);
                t.setCatrev(cr);
                transactions.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
