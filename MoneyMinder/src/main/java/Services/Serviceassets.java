package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Assets;
import Models.CatAssets;
import Models.User;
import Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serviceassets implements InterfaceMoneyMinder<Assets> {
    private final Connection connection;

    public Serviceassets() {
        this.connection = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Assets assets) {
        String query = "INSERT INTO assets (id_asset, titre, prix, id_categ,id_user,statut ) VALUES (?,?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, assets.getId_assets());
            pstmt.setString(2, assets.getTitre());
            pstmt.setFloat(3, assets.getPrix());
            pstmt.setInt(4, assets.getId_catassets().getId_cat());
            pstmt.setInt(5, assets.getId_user().getId_user());
            pstmt.setString(6, assets.getStatut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Assets assets) {
        String query = "DELETE FROM assets WHERE titre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, assets.getTitre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Assets assets) {
        String query = "UPDATE assets SET titre = ?, prix = ?, id_categ = ?, id_user = ?, statut = ? WHERE id_asset= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, assets.getTitre());
            pstmt.setFloat(2, assets.getPrix());
            pstmt.setInt(3, assets.getId_catassets().getId_cat());
            pstmt.setInt(4, assets.getId_user().getId_user());
            pstmt.setString(5, assets.getStatut());
            pstmt.setInt(6, assets.getId_assets());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Assets> getAll() {
        List<Assets> assetsList = new ArrayList<>();
        String query = "SELECT * FROM assets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Assets asset = new Assets(rs.getInt("id_asset"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_categ")), new User(rs.getInt("id_user")), rs.getString("statut"));
                assetsList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetsList;
    }


    @Override
    public List<Assets> getbyfilter(String filterQuery) {
        List<Assets> assetsList = new ArrayList<>();
        String query = "SELECT * FROM assets WHERE " + filterQuery;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Assets asset = new Assets(rs.getInt("id_asset"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_categ")), new User(rs.getInt("id_user")), rs.getString("statut"));
                assetsList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetsList;
    }


    @Override
    public List<Assets> getbyid(int id) {
        List<Assets> assetsList = new ArrayList<>();
        String query = "SELECT * FROM assets WHERE id_asset = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assets asset = new Assets(rs.getInt("id_asset"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_categ")), new User(rs.getInt("id_user")), rs.getString("statut"));
                assetsList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetsList;

    }

//    // Méthode pour récupérer la catégorie d'actifs par son identifiant
//    private CatAssets getCatAssetsById(int id) {
//        String query = "SELECT * FROM categorieassets WHERE id_cat = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return new CatAssets(
//                        rs.getInt("id_cat"),
//                        rs.getString("categ")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    // Business method: Calculate the total value of assets for a given user
    public float calculateTotalValueByUser(int userId) {
        String query = "SELECT SUM(prix) AS total_value FROM assets WHERE id_user = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("total_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    // Method to buy an asset
    public void acheter(Assets asset) {
        asset.setStatut("acheté");
        update(asset);
    }

    // Method to reserve an asset
    public void reserver(Assets asset) {
        asset.setStatut("réservé");
        update(asset);
    }
}