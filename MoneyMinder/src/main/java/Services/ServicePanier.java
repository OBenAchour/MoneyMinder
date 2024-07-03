package Services;

import Models.Panier;
import Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier {
    private static ServicePanier instance;
    private Connection connection;

    private ServicePanier() {
        connection = MyConnexion.getInstance().getCnx();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    public void add(Panier asset) {
        String query = "INSERT INTO panier (IdAsset, Titre, Prix) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, asset.getIdAssets());
            pstmt.setString(2, asset.getTitre());
            pstmt.setFloat(3, asset.getPrix());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Panier asset) {
        String query = "DELETE FROM panier WHERE IdAsset = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, asset.getIdAssets());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Panier> getAll() {
        List<Panier> panierList = new ArrayList<>();
        String query = "SELECT * FROM panier";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Panier panier = new Panier();
                panier.setIdAssets(rs.getInt("IdAsset"));
                panier.setTitre(rs.getString("Titre"));
                panier.setPrix(rs.getFloat("Prix"));
                panierList.add(panier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return panierList;
    }
}
