package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.CatDep;
import Models.Catrev;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatdepService implements InterfaceMoneyMinder<CatDep> {

    //var
    Connection cnx= Myconnection.getInstance().getCnx();
    @Override
    public void add(CatDep catDep) {
        String req ="INSERT INTO `categoriedep`(`catDep`) VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, catDep.getType());
            ps.executeUpdate();
            System.out.println("categorie depense ajoutée avec succees");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CatDep catDep) {
        String req="DELETE FROM `categoriedep` WHERE idCatDep= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,catDep.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


       @Override
       public void update(CatDep catDep) {
           String req = "UPDATE `categoriedep` SET `catDep` =? WHERE `idCatDep` =?";
           try {
               PreparedStatement ps = cnx.prepareStatement(req);
               ps.setString(1, catDep.getType());
               ps.setInt(2, catDep.getId());
               ps.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

    @Override
    public List<CatDep> getAll() {
        List<CatDep> catdeps = new ArrayList<>();
        String req = "SELECT * FROM `categoriedep`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                CatDep catdep = new CatDep();
                catdep.setId(res.getInt("idCatDep"));
                catdep.setType(res.getString("catDep"));
                catdeps.add(catdep);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return catdeps;
    }

    @Override
    public List<CatDep> getbyfilter(String filter) {
        List<CatDep> catdeps = new ArrayList<>();
        String req ="SELECT * FROM `categoriedep`"+filter;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                CatDep catdep = new CatDep();
                catdep.setId(res.getInt("idCatDep"));
                catdep.setType(res.getString("catDep"));
                catdeps.add(catdep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catdeps;

    }

    @Override
    public List<CatDep> getbyid(int id) {
        List<CatDep> catdeps = new ArrayList<>();
        String req="SELECT * FROM `categoriedep`WHERE idCatDep="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                CatDep catdep = new CatDep();
                catdep.setId(res.getInt("idCatDep"));
                catdep.setType(res.getString("catDep"));
                catdeps.add(catdep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catdeps;
    }
}
