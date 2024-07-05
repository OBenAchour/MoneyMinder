//package Services;
//
//import Models.AchatAssets;
//import Models.Panier;
//import Utils.MyConnexion;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServiceAchatAssets {
//    private static ServiceAchatAssets instance;
//    private Connection connection;
//
//    private ServiceAchatAssets() {
//        connection = MyConnexion.getInstance().getCnx();
//    }
//
//    public static ServiceAchatAssets getInstance() {
//        if (instance == null) {
//            instance = new ServiceAchatAssets();
//        }
//        return instance;
//    }
//
//    public void add(Panier asset) {
//        String query = "INSERT INTO achat (IdAsset, Titre, Prix, DateAchat) VALUES (?, ?, ?, CURRENT_DATE)";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setInt(1, asset.getIdAssets());
//            pstmt.setString(2, asset.getTitre());
//            pstmt.setFloat(3, asset.getPrix());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<AchatAssets> getAll() {
//        List<AchatAssets> achatList = new ArrayList<>();
//        String query = "SELECT * FROM achat";
//        try (PreparedStatement pstmt = connection.prepareStatement(query);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                AchatAssets achat = new AchatAssets();
//                achat.setId_achat(rs.getInt("IdAsset"));
//                achat.setTitre(rs.getString("Titre"));
//                achat.setPrix(rs.getFloat("Prix"));
//                achat.setDate_achat(rs.getDate("DateAchat"));
//                achatList.add(achat);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return achatList;
//    }
//
//
//}
package Services;

import Models.*;
import Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<Assets> getNeverSoldAssetsWithDiscount() {
        List<Assets> assetsList = new ArrayList<>();
        String query = "SELECT * FROM assets WHERE id_assets NOT IN (SELECT IdAsset FROM achat)";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Assets asset = new Assets();
                asset.setId_assets(rs.getInt("id_assets"));
                asset.setTitre(rs.getString("titre"));
                asset.setPrix(rs.getFloat("prix") * 0.8f); // Apply 20% discount
                asset.setId_catassets(new CatAssets(rs.getInt("id_catassets")));
                asset.setId_user(new User(rs.getInt("id_user")));
                asset.setStatut(rs.getString("statut"));
                assetsList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetsList;
    }
    public float getTotalMonthPurchase() {
        float totalMonthPurchase = 0;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Les mois en Java Calendar sont indexés à partir de 0

        String query = "SELECT SUM(Prix) AS TotalMonthPurchase FROM achat WHERE YEAR(DateAchat) = ? AND MONTH(DateAchat) = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalMonthPurchase = rs.getFloat("TotalMonthPurchase");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalMonthPurchase;
    }
}
