package Services;

import Models.Portefeuille_actions;
import Models.User;
import Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PortefeuilleActionService  {
    private Connection connection;

    public PortefeuilleActionService() {
        this.connection = MyConnexion.getInstance().getCnx();
    }

    public void add(Portefeuille_actions portefeuille) {
        String query = "INSERT INTO portefeuille_actions (date_creation, solde_investissement, id_user) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, new java.sql.Date(portefeuille.getDate_creation().getTime()));
            pstmt.setFloat(2, portefeuille.getSolde_inves());
            pstmt.setInt(3, portefeuille.getId_user().getId());
            pstmt.executeUpdate();
            System.out.println("Portefeuille ajouté avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du portefeuille : " + e.getMessage());
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
            pstmt.setInt(3, portefeuille.getId_user().getId());
            pstmt.setInt(4, portefeuille.getId_portefeuille());
            pstmt.executeUpdate();
            System.out.println("Portefeuille mis à jour avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour du portefeuille : " + e.getMessage());
        }
    }

    public List<Portefeuille_actions> getAll() {
        List<Portefeuille_actions> portefeuilles = new ArrayList<>();
        String query = "SELECT * FROM portefeuille_actions";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Portefeuille_actions portefeuille = new Portefeuille_actions();
                portefeuille.setId_portefeuille(rs.getInt("id_portefeuille"));
                portefeuille.setDate_creation(rs.getDate("date_creation"));
                portefeuille.setSolde_inves(rs.getFloat("solde_investissement"));
                // Supposons que la classe User ait un constructeur qui accepte un ID.
                portefeuille.setId_user(new User(rs.getInt("id_user")));
                portefeuilles.add(portefeuille);
            }
            System.out.println("Portefeuilles récupérés avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des portefeuilles : " + e.getMessage());
        }
        return portefeuilles;
    }

}
