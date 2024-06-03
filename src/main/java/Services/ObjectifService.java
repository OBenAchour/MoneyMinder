package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Objectif;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectifService implements InterfaceMoneyMinder<Objectif> {

    private Connection connectDB;

    @Override
    public void add(Objectif objectif) {
        String insertQueryObjectif = "INSERT INTO `objectif`(`titre`, `montant_globale`, `echeance`, `mois`, `annee`, `commentaire`, `id_cat_obj`, `id_wallet`) VALUES (?,?,?,?,?,?,?,?)";

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
            ps.setInt(5, objectif.getAnnee());
            ps.setString(6, objectif.getCommentaire());

            // Vérification de null pour Catobj
            if (objectif.getCatobj() != null) {
                ps.setInt(7, objectif.getCatobj().getId_obj());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            ps.setInt(8, objectif.getId_wallet());
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

        String req = "DELETE FROM `objectif` WHERE id = ? ";


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

        String req = "UPDATE `objectif` SET `titre` = ?, `montant_globale` = ?, `echeance` = ?, `mois` = ?, `annee` = ?, `commentaire` = ?, `id_cat_obj` = ?, `id_wallet` = ? WHERE `id` = ?";
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
            ps.setInt(5, objectif.getAnnee());
            ps.setString(6, objectif.getCommentaire());
            ps.setInt(7, objectif.getCatobj().getId_obj());
            ps.setInt(8, objectif.getId_wallet());
            ps.setInt(9, objectif.getId_obj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Objectif> getAll() {
        List<Objectif> objectifs = new ArrayList<>();
        Myconnection connectNow = new Myconnection();
        connectDB = connectNow.getCnx();
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
                objectif.setAnnee(rs.getInt("annee"));
                objectif.setCommentaire(rs.getString("commentaire"));

                objectifs.add(objectif);
            }

            return objectifs;
        } catch (SQLException e) {
            // Gérer les erreurs de récupération des données
            e.printStackTrace();
            return null; // ou une liste vide, ou lancez une exception si nécessaire
        }
    }

    @Override
    public List<Objectif> getbyfilter() {
        List<Objectif> filteredObjectifs = new ArrayList<>();
        Myconnection connectNow = new Myconnection();
        connectDB = connectNow.getCnx();
        try {
            String query = "SELECT * FROM `objectif` WHERE titre=? ";
            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setString(1, "%filterTitre%");


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id_obj"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setAnnee(rs.getInt("annee"));
                objectif.setCommentaire(rs.getString("commentaire"));
                // Ajoutez d'autres attributs si nécessaire

                filteredObjectifs.add(objectif);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredObjectifs;

    }

    @Override
    public List<Objectif> getbyid() {
        List<Objectif> Objectifs = new ArrayList<>();
        Myconnection connectNow = new Myconnection();
        connectDB = connectNow.getCnx();
        try {
            // Créez la requête SQL pour récupérer les objectifs en fonction de certains critères
            String query = "SELECT * FROM `objectif` WHERE id=? ";
            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setString(1, "%filterid%");
            // Vous pouvez également définir des paramètres pour les critères de filtrage si nécessaire
            // ps.setXXX(index, value);

            ResultSet rs = ps.executeQuery();

            // Parcourez les résultats et ajoutez chaque objectif filtré à la liste
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id_obj"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setEcheance(rs.getDouble("echeance"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setAnnee(rs.getInt("annee"));
                objectif.setCommentaire(rs.getString("commentaire"));
                // Ajoutez d'autres attributs si nécessaire

                Objectifs.add(objectif);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Objectifs;

    }

}


