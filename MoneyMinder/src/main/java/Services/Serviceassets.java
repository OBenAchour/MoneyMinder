package Services;
import Interfaces.InterfaceMoneyMinder;
import Models.Assets;
import Models.CatAssets;
import Models.User;
import Utils.MyConnexion;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Serviceassets implements InterfaceMoneyMinder<Assets> {
    private final Connection connection;


    public Serviceassets() {
        this.connection = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Assets assets) {
        String query = "INSERT INTO assets (titre, prix, id_cat) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, assets.getTitre());
            pstmt.setFloat(2, assets.getPrix());
            pstmt.setInt(3, assets.getId_catassets().getId_cat()); // Assurez-vous que `CatAssets` a un `getId_catassets()`
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }


    @Override
    public void delete(Assets assets) {
        String query = "DELETE FROM assets WHERE id_asset = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, assets.getId_assets());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Assets assets) {
        String query = "UPDATE assets SET titre = ?, prix = ?, id_cat = ? WHERE id_assets = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, assets.getTitre());
            pstmt.setFloat(2, assets.getPrix());
            pstmt.setFloat(3, assets.getId_catassets().getId_cat());
            pstmt.setInt(4, assets.getId_assets());
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
                // Assurez-vous de remplir les champs `CatAsset` et `User` correctement
                Assets asset = new Assets(rs.getInt("id_asset"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_cat")));
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
                Assets asset = new Assets(rs.getInt("id_asset"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_cat")));
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
        String query = "SELECT * FROM assets WHERE id_assets = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assets asset = new Assets(rs.getInt("id_assets"), rs.getString("titre"), rs.getFloat("prix"), new CatAssets(rs.getInt("id_cat")));
                assetsList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetsList;
    }
}

