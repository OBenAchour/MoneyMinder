package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Objectif;
import Models.Catobj;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectifService implements InterfaceMoneyMinder<Objectif> {

    private Connection connectDB;

    public ObjectifService() {
        Myconnection connectNow = Myconnection.getInstance();
        connectDB = connectNow.getCnx();
    }

    @Override
    public void add(Objectif objectif) {
        String insertQueryObjectif = "INSERT INTO `objectif`(`titre`, `montant_globale`, `echeance`, `mois`, `commentaire`, `id_cat_obj`, `id_wallet`) VALUES (?,?,?,?,?,?,?)";

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
            throw new RuntimeException("Erreur lors de l'ajout de l'objectif", e);
        }
    }

    @Override
    public void delete(Objectif objectif) {
        String query = "DELETE FROM objectif WHERE id = ?";
        try (PreparedStatement statement = connectDB.prepareStatement(query)) {
            statement.setInt(1, objectif.getId_obj());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    @Override
    public void update(Objectif objectif) {
        String req = "UPDATE `objectif` SET `titre` = ?, `montant_globale` = ?, `mois` = ?, `commentaire` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
           // ps.setDouble(3, objectif.getEcheance());
            ps.setInt(3, objectif.getMois());
            ps.setString(4, objectif.getCommentaire());
           // if (objectif.getCatobj() != null) {
               // ps.setInt(6, objectif.getCatobj().getId_obj());
           // } else {
               // ps.setNull(6, java.sql.Types.INTEGER);
            //}
            //ps.setInt(7, objectif.getId_wallet());
            ps.setInt(5, objectif.getId_obj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'objectif", e);
        }
    }

    @Override
    public List<Objectif> getAll() {
        List<Objectif> objectifs = new ArrayList<>();
        try {
            String query = "SELECT * FROM `objectif`";
            PreparedStatement ps = connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                //objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                // You might want to fetch Catobj here and set it to objectif
                // Example:
                // Catobj catobj = new Catobj();
                // catobj.setId_obj(rs.getInt("id_cat_obj"));
                // objectif.setCatobj(catobj);

                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des objectifs", e);
        }
        return objectifs;
    }

    @Override
    public List<Objectif> getbyfilter(String column, String value) {
        List<Objectif> filteredObjectifs = new ArrayList<>();
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
                //objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                // You might want to fetch Catobj here and set it to objectif
                // Example:
                // Catobj catobj = new Catobj();
                // catobj.setId_obj(rs.getInt("id_cat_obj"));
                // objectif.setCatobj(catobj);

                filteredObjectifs.add(objectif);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des objectifs filtrés", e);
        }
        return filteredObjectifs;
    }

    @Override
    public List<Objectif> getbyid(int id) {
        List<Objectif> objectifs = new ArrayList<>();
        String query = "SELECT * FROM `objectif` WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                //objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));
                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'objectif par ID", e);
        }
        return objectifs;
    }



    public void calculerEcheance() {
        List<Objectif> objectifs = getAll();
        for (Objectif objectif : objectifs) {
            int dureeTotaleMois = objectif.getMois();
            double echeance = objectif.getMontant_globale() / dureeTotaleMois;
            objectif.setEcheance(echeance);
            update(objectif);
            System.out.println("L'échéance pour l'objectif '" + objectif.getTitre() + "' est de : " + echeance);
        }
    }

   // private void updateEcheance(int objectId, double echeance) {
     //   String updateQuery = "UPDATE `objectif` SET `echeance` = ? WHERE `id` = ?";
       // try {
         //   PreparedStatement ps = connectDB.prepareStatement(updateQuery);
           // ps.setDouble(1, echeance);
            //ps.setInt(2, objectId);
            //ps.executeUpdate();
        //} catch (SQLException e) {
          //  throw new RuntimeException("Erreur lors de la mise à jour de l'échéance", e);
        //}
    //}
}
