package Services;

import Models.Action;
import Models.Portefeuille_actions;
import Models.User;
import Utils.MyConnexion;
import Utils.RestClient;
import Utils.UserUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortefeuilleActionService  {
    private RestClient rs = new RestClient();
    private Connection connection;

    public PortefeuilleActionService() {
        this.connection = MyConnexion.getInstance().getCnx();
    }

    public void add(Portefeuille_actions portefeuille)throws SQLException {
        String query = "INSERT INTO portefeuille_actions (nom, cours, quantite, total, date_creation, type,id_user) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, portefeuille.getNom());
            pstmt.setFloat(2, portefeuille.getCours());
            pstmt.setFloat(3, portefeuille.getQuantite());
            pstmt.setFloat(4, portefeuille.getTotal());
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(portefeuille.getDate_creation().getTime());
            pstmt.setTimestamp(5, sqlTimestamp);
            pstmt.setString(6, portefeuille.getType());
            pstmt.setInt(7, portefeuille.getId_user());
            pstmt.executeUpdate();
        }
    }

    public void delete(Portefeuille_actions portefeuille) {
        String query = "DELETE FROM portefeuille_actions WHERE id_portefeuille = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, portefeuille.getId_portefeuille());
            pstmt.executeUpdate();
            System.out.println("Portefeuille supprimé avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du portefeuille : " + e.getMessage());
        }
    }

    public void update(Portefeuille_actions portefeuille) {
        String query = "UPDATE portefeuille_actions SET date_creation = ?, solde_investissement = ?, id_user = ? WHERE id_portefeuille = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, new java.sql.Date(portefeuille.getDate_creation().getTime()));
            pstmt.setFloat(2, portefeuille.getSolde_inves());
            pstmt.setInt(3, portefeuille.getId_user());
            pstmt.setInt(4, portefeuille.getId_portefeuille());
            pstmt.executeUpdate();
            System.out.println("Portefeuille mis à jour avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour du portefeuille : " + e.getMessage());
        }
    }

    public void updateQuantite(Portefeuille_actions portefeuille) {
        String query = "UPDATE portefeuille_actions SET quantite = ? WHERE id_portefeuille = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, portefeuille.getQuantite());
            pstmt.setInt(2, portefeuille.getId_portefeuille());
            pstmt.executeUpdate();
            System.out.println("Portefeuille mis à jour avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour du portefeuille : " + e.getMessage());
        }
    }

    public List<String> getAllNom() {
        List<String> noms = new ArrayList<>();
        String query = "SELECT nom FROM portefeuille_actions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                noms.add(rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(noms);
        return noms;
    }

    public void updateQuantite() {
        float quantite=0;
        List<String> noms = getAllNom();
        for (String nom: noms) {
            quantite=rs.getUpdateVente(nom);
            String query = "UPDATE portefeuille_actions SET quantite = ? AND  WHERE nom = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setFloat(1, quantite);
                pstmt.setString(2, nom);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



//    public List<Portefeuille_actions> getAll() {
//        List<Portefeuille_actions> portefeuilles = new ArrayList<>();
//        String query = "SELECT * FROM portefeuille_actions";
//        try (PreparedStatement pstmt = connection.prepareStatement(query);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                Portefeuille_actions portefeuille = new Portefeuille_actions();
//                portefeuille.setId_portefeuille(rs.getInt("id_portefeuille"));
//                portefeuille.setDate_creation(rs.getDate("date_creation"));
//                portefeuille.setSolde_inves(rs.getFloat("solde_investissement"));
//                // Supposons que la classe User ait un constructeur qui accepte un ID.
//                portefeuille.setId_user(new User(rs.getInt("id_user")));
//                portefeuilles.add(portefeuille);
//            }
//            System.out.println(portefeuilles);
//            System.out.println("Portefeuilles récupérés avec succès!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Erreur lors de la récupération des portefeuilles : " + e.getMessage());
//        }
//        return portefeuilles;
//    }



    public List<Portefeuille_actions> getAll() throws SQLException {
        List<Portefeuille_actions> actions = new ArrayList<>();
        String query = "SELECT * FROM portefeuille_actions where  id_user=?";
        try ( PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setFloat(1, UserUtil.userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Portefeuille_actions action = new Portefeuille_actions(resultSet.getFloat("total"),resultSet.getInt("quantite"), resultSet.getString("nom"), resultSet.getFloat("cours"));
                action.setType(resultSet.getString("type"));
                action.setDate_creation(resultSet.getTimestamp("date_creation"));
                action.setId_portefeuille(resultSet.getInt("id_portefeuille"));
                actions.add(action);
            }
        }
        return actions;
    }

    public void addtoPortefeuille(Action action) throws SQLException {
        String query = "INSERT INTO portefeuill_actions (nom, prix_achat, cours, prix_vente, quantite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, action.getNom());
            pstmt.setFloat(2, action.getPrix_achat());
            pstmt.setFloat(3, action.getCours());
            pstmt.setFloat(4, action.getPrix_vente());
            pstmt.setInt(5, action.getQuantite());
            pstmt.executeUpdate();
        }
    }

}
