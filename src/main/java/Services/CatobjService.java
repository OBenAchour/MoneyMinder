package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Catobj;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatobjService implements InterfaceMoneyMinder<Catobj> {

    private Connection connection;



    @Override
    public void add(Catobj catobj) {

        if (catobj.getCatobj() == null || catobj.getCatobj().isEmpty())
        {
            System.out.println(" La catégorie d'objet ne peut pas être vide.");
            return;
        }
        if (!catobj.getCatobj().equals("Voyage") &&!catobj.getCatobj().equals("Véhicule") &&!catobj.getCatobj().equals("Immobilier") &&!catobj.getCatobj().equals("autre"))
        {
            System.out.println(" La catégorie d'objet doit être l'un des suivants : Voyage, Véhicule, Immobilier ou autre.");
            return;
        }

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "INSERT INTO `categorieobj`( `catObj`) VALUES (?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, catobj.getCatobj());
            pstmt.executeUpdate();
            System.out.println(" Ajout avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Catobj catobj) {

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "DELETE FROM `categorieobj` WHERE  idObj =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, catobj.getId_obj());
            pstmt.executeUpdate();
            System.out.println(" Suppression avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Catobj catobj) {

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "UPDATE `categorieobj` SET `catObj`= ? WHERE idObj =? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, catobj.getCatobj());
            pstmt.setInt(2, catobj.getId_obj());
            pstmt.executeUpdate();
            System.out.println(" Modification avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Catobj> getAll() {
        List<Catobj> catobjs = new ArrayList<>();

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "SELECT * FROM `categorieobj`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Catobj catobj = new Catobj();
                catobj.setId_obj(resultSet.getInt("idObj"));
                catobj.setCatobj(resultSet.getString("catobj"));
                catobjs.add(catobj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return catobjs;
    }

    @Override
    public List<Catobj> getbyfilter(String column, String value) {
        List<Catobj> catobjs = new ArrayList<>();

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "SELECT * FROM `categorieobj` WHERE " + column + " =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, value);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Catobj catobj = new Catobj();
                catobj.setId_obj(resultSet.getInt("idObj"));
                catobj.setCatobj(resultSet.getString("catobj"));
                catobjs.add(catobj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return catobjs;
        }

    @Override
    public List<Catobj> getbyid(int id)  {
        List<Catobj> catobjs = new ArrayList<>();

        if (connection == null) {
            Myconnection connectNow = new Myconnection();
            connection = connectNow.getCnx();
        }

        String query = "SELECT * FROM `categorieobj` WHERE idObj =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Catobj catobj = new Catobj();
                catobj.setId_obj(resultSet.getInt("idObj"));
                catobj.setCatobj(resultSet.getString("catobj"));
                catobjs.add(catobj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catobjs;
    }
    }



