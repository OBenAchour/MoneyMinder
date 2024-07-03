package Services;

import Models.AchatAssets;
import Models.Panier;
import Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAchatAssets {
    private static ServiceAchatAssets instance;
    private Connection connection;

    private ServiceAchatAssets() {
        connection = MyConnexion.getInstance().getCnx();
    }

    public static ServiceAchatAssets getInstance() {
        if (instance == null) {
            instance = new ServiceAchatAssets();
        }
        return instance;
    }

    public void add(Panier asset) {
        String query = "INSERT INTO achat (IdAsset, Titre, Prix, DateAchat) VALUES (?, ?, ?, CURRENT_DATE)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, asset.getIdAssets());
            pstmt.setString(2, asset.getTitre());
            pstmt.setFloat(3, asset.getPrix());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<AchatAssets> getAll() {
        List<AchatAssets> achatList = new ArrayList<>();
        String query = "SELECT * FROM achat";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                AchatAssets achat = new AchatAssets();
                achat.setId_achat(rs.getInt("IdAsset"));
                achat.setTitre(rs.getString("Titre"));
                achat.setPrix(rs.getFloat("Prix"));
                achat.setDate_achat(rs.getDate("DateAchat"));
                achatList.add(achat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achatList;
    }

}
