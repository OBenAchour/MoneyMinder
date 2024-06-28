package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Catobj;
import Models.Objectif;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatobjService implements InterfaceMoneyMinder<Catobj> {

    private Connection connection;

    public CatobjService() {
        Myconnection connectNow = Myconnection.getInstance();
        connection = connectNow.getCnx();
    }

    @Override
    public void add(Catobj catobj) {
        if (catobj.getCatobj() == null || catobj.getCatobj().isEmpty()) {
            System.out.println("La catégorie d'objet ne peut pas être vide.");
            return;
        }
        if (!catobj.getCatobj().equals("Voyage") && !catobj.getCatobj().equals("Véhicule") &&
                !catobj.getCatobj().equals("Immobilier") && !catobj.getCatobj().equals("autre")) {
            System.out.println("La catégorie d'objet doit être l'un des suivants : Voyage, Véhicule, Immobilier ou autre.");
            return;
        }

        String query = "INSERT INTO `categorieobj`(`catObj`) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, catobj.getCatobj());
            pstmt.executeUpdate();
            System.out.println("Ajout avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la catégorie d'objet", e);
        }
    }

    @Override
    public void delete(Catobj catobj) {
        String query = "DELETE FROM `categorieobj` WHERE `idObj` = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, catobj.getId_obj());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune catégorie d'objet trouvée avec cet ID.");
            } else {
                System.out.println("Suppression avec succès.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la catégorie d'objet", e);
        }
    }

    @Override
    public void update(Catobj catobj) {
        String query = "UPDATE `categorieobj` SET `catObj` = ? WHERE `idObj` = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, catobj.getCatobj());
            pstmt.setInt(2, catobj.getId_obj());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune catégorie d'objet trouvée avec cet ID.");
            } else {
                System.out.println("Modification avec succès.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la catégorie d'objet", e);
        }
    }

    @Override
    public List<Catobj> getAll() {
        List<Catobj> catobjs = new ArrayList<>();
        String query = "SELECT * FROM `categorieobj`";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                Catobj catobj = new Catobj();
                catobj.setId_obj(resultSet.getInt("idObj"));
                catobj.setCatobj(resultSet.getString("catObj"));
                catobjs.add(catobj);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les catégories d'objet", e);
        }
        return catobjs;
    }

    @Override
    public List<Catobj> getbyfilter(String column, String value) {
        List<Catobj> catobjs = new ArrayList<>();
        String query = "SELECT * FROM `categorieobj` WHERE " + column + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, value);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Catobj catobj = new Catobj();
                    catobj.setId_obj(resultSet.getInt("idObj"));
                    catobj.setCatobj(resultSet.getString("catObj"));
                    catobjs.add(catobj);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des catégories d'objet filtrées", e);
        }
        return catobjs;
    }

    @Override
    public List<Catobj> getbyid(int id) {
        List<Catobj> catobjs = new ArrayList<>();
        String query = "SELECT * FROM `categorieobj` WHERE `idObj` = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    Catobj catobj = new Catobj();
                    catobj.setId_obj(resultSet.getInt("idObj"));
                    catobj.setCatobj(resultSet.getString("catObj"));
                    catobjs.add(catobj);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la catégorie d'objet par ID", e);
        }
        return catobjs;
    }

    @Override
    public List<Objectif> getByFilter(String column, String value) {
        // Implémentation à adapter si nécessaire pour les objectifs liés à la catégorie d'objet
        return new ArrayList<>();
    }
}
