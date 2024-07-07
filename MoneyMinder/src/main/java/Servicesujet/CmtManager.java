package Servicesujet;

import Interfaces.InterfaceMoneyMinder;
import Models.Commentaire;
import Models.Sujet;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmtManager implements InterfaceMoneyMinder<Commentaire> {
    private Connection connection;

    public CmtManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Commentaire commentaire) {

        String query = "INSERT INTO commentaire (cmt, id_sujet) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, commentaire.getCmt());
            pstmt.setInt(2, commentaire.getId_sujet().getId_suj());
            pstmt.executeUpdate();
            System.out.println("Commentaire ajouté : " + commentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Commentaire commentaire) {
            String query = "DELETE FROM commentaire WHERE id_cmt = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, commentaire.getId_comm());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Commentaire supprimé : " + commentaire);
                } else {
                    System.out.println("Erreur : Aucun commentaire trouvé avec cet ID.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void update(Commentaire commentaire) {
        String query = "UPDATE commentaire SET cmt = ? WHERE id_cmt = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, commentaire.getCmt());
            pstmt.setInt(2, commentaire.getId_comm());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Commentaire mis à jour : " + commentaire);
            } else {
                System.out.println("Erreur : Aucun commentaire trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commentaire> getAll() {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_cmt = rs.getInt("id_cmt");
                String cmt = rs.getString("cmt");
                int id_sujet = rs.getInt("id_sujet");
                // Assurez-vous d'avoir une classe Sujet avec un constructeur approprié
                Sujet sujet = new Sujet(id_sujet);

                Commentaire commentaire = new Commentaire(id_cmt, cmt, sujet);
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }


    @Override
    public List<Commentaire> getbyfilter() {
        List<Commentaire> commentaires = new ArrayList<>();
        String filterValue = "Important"; // Valeur de filtre fixe pour les commentaires contenant "Important"
        String query = "SELECT * FROM commentaire WHERE cmt LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + filterValue + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_cmt = rs.getInt("id_cmt");
                String cmt = rs.getString("cmt");
                int id_sujet = rs.getInt("id_sujet");
                // Assurez-vous d'avoir une classe Sujet avec un constructeur approprié
                Sujet sujet = new Sujet(id_sujet);

                Commentaire commentaire = new Commentaire(id_cmt, cmt, sujet);
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }
    @Override
    public List<Commentaire> getbyid() {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire WHERE id_cmt = ?";

        // Pour cet exemple, nous fixons un ID spécifique, par exemple 1
        int id = 40;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_cmt = rs.getInt("id_cmt");
                String cmt = rs.getString("cmt");
                int id_sujet = rs.getInt("id_sujet");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }








}