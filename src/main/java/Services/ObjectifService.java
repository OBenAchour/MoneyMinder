package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Objectif;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectifService implements InterfaceMoneyMinder<Objectif> {

    private Connection connectDB;

    @Override
    public void add(Objectif objectif) {
        String insertQueryObjectif = "INSERT INTO `objectif`(`titre`, `montant_globale`, `echeance`, `mois`, `commentaire`, `id_cat_obj`, `id_wallet`) VALUES (?,?,?,?,?,?,?)";

        if (connectDB == null) {
            Myconnection connectNow = new Myconnection();
            connectDB = connectNow.getCnx();
        }

        try {
            PreparedStatement ps = connectDB.prepareStatement(insertQueryObjectif);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
            ps.setDouble(3, objectif.getEcheance());
            ps.setInt(4, objectif.getMois());
            ps.setString(5, objectif.getCommentaire());

            if (objectif.getCatobj() != null) {
                ps.setInt(6, objectif.getCatobj().getId_obj());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setInt(7, objectif.getId_wallet());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void calculerEcheance() {
        List<Objectif> objectifs = getAll();

        for (Objectif objectif : objectifs) {
            int dureeTotaleMois =objectif.getMois();
            double echeance = objectif.getMontant_globale() / dureeTotaleMois;
            objectif.setEcheance(echeance);

            updateEcheance(objectif.getId_obj(), echeance);

            System.out.println("L'échéance pour l'objectif '" + objectif.getTitre() + "' est de : " + echeance);
        }
    }

    private void updateEcheance(int objectId, double echeance) {
        String updateQuery = "UPDATE `objectif` SET `echeance` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(updateQuery);
            ps.setDouble(1, echeance);
            ps.setInt(2, objectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Objectif objectif) {
        if (connectDB == null) {
            Myconnection connectNow = new Myconnection();
            connectDB = connectNow.getCnx();
        }
        String req = "DELETE FROM `objectif` WHERE id = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setInt(1, objectif.getId_obj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Objectif objectif) {
        String req = "UPDATE `objectif` SET `titre` = ?, `montant_globale` = ?, `echeance` = ?, `mois` = ?, `commentaire` = ?, `id_cat_obj` = ?, `id_wallet` = ? WHERE `id` = ?";
        if (connectDB == null) {
            Myconnection connectNow = new Myconnection();
            connectDB = connectNow.getCnx();
        }
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
            ps.setDouble(3, objectif.getEcheance());
            ps.setInt(4, objectif.getMois());
            ps.setString(5, objectif.getCommentaire());
            ps.setInt(6, objectif.getCatobj().getId_obj());
            ps.setInt(7, objectif.getId_wallet());
            ps.setInt(8, objectif.getId_obj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Objectif> getAll() {
        List<Objectif> objectifs = new ArrayList<>();
        if (connectDB == null) {
            Myconnection connectNow = new Myconnection();
            connectDB = connectNow.getCnx();
        }
        try {
            String query = "SELECT * FROM `objectif`";
            PreparedStatement ps = connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));
                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return objectifs;
    }

    @Override
    public List<Objectif> getbyfilter(String column, String value) {
        List<Objectif> filteredObjectifs = new ArrayList<>();
        if (connectDB == null) {
            Myconnection cnx = new Myconnection();
            connectDB = cnx.getCnx();
        }
        String req = "SELECT * FROM `objectif` WHERE " + column + " = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));
                filteredObjectifs.add(objectif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredObjectifs;
    }

    @Override
    public List<Objectif> getbyid(int id) {
        List<Objectif> objectifs = new ArrayList<>();
        if (connectDB == null) {
            Myconnection connectNow = new Myconnection();
            connectDB = connectNow.getCnx();
        }
        try {
            String query = "SELECT * FROM `objectif` WHERE `id` = ?";
            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectifs;
    }
}
