package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.User;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserService implements InterfaceMoneyMinder<User> {

    Connection cnx = Myconnection.getInstance().getCnx();
    @Override
    public void add(User user) {
        String req = "INSERT INTO `user`(`nom`, `prenom`, `Date_naissance`, `tel`, `mail`, `Mot_de_passe`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setDate(3, user.getDate_de_naiss());
            ps.setInt(4, user.getTel());
            ps.setString(5,user.getMail());
            ps.setString(6,user.getMot_de_passe());
            ps.executeUpdate();
            System.out.println("Personne ajoutée avec succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getbyfilter() {
        return null;
    }

    @Override
    public List<User> getbyid() {
        return null;
    }
}
