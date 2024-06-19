package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.CatAssets;
import Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCatAssets implements InterfaceMoneyMinder<CatAssets> {

    private final Connection connection;

    public ServiceCatAssets() {
        this.connection = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void add(CatAssets catAssets) {
        String query = "INSERT INTO categorieassets (categ) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, catAssets.getCateg());
            pstmt.executeUpdate();

            // Récupérer l'identifiant généré
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    catAssets.setId_cat(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CatAssets catAssets) {
        String query = "DELETE FROM categorieassets WHERE id_cat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, catAssets.getId_cat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CatAssets catAssets) {
        String query = "UPDATE categorieassets SET categ = ? WHERE id_cat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, catAssets.getCateg());
            pstmt.setInt(2, catAssets.getId_cat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CatAssets> getAll() {
        List<CatAssets> catAssetsList = new ArrayList<>();
        String query = "SELECT * FROM categorieassets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CatAssets catAssets = new CatAssets(rs.getInt("id_cat"), rs.getString("categ"));
                catAssetsList.add(catAssets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catAssetsList;
    }

    @Override
    public List<CatAssets> getbyfilter(String filterQuery) {
        List<CatAssets> catAssetsList = new ArrayList<>();
        String query = "SELECT * FROM categorieassets WHERE " + filterQuery;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CatAssets catAssets = new CatAssets(rs.getInt("id_cat"), rs.getString("categ"));
                catAssetsList.add(catAssets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catAssetsList;
    }

    @Override
    public List<CatAssets> getbyid(int id) {
        List<CatAssets> catAssetsList = new ArrayList<>();
        String query = "SELECT * FROM categorieassets WHERE id_cat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                CatAssets catAssets = new CatAssets(rs.getInt("id_cat"), rs.getString("categ"));
                catAssetsList.add(catAssets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catAssetsList;
    }
}
