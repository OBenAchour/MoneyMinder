package Servicesujet;

import Interfaces.InterfaceMoneyMinder;
import Models.Sujet;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SujetManager implements InterfaceMoneyMinder<Sujet> {
    private Connection connection;

    public SujetManager(Connection connection) {

        this.connection = connection;
    }

    @Override
    public void add(Sujet sujet) {
        if (sujet.getTitre() == null || sujet.getTitre().isEmpty()) {
            System.out.println("Erreur : Le titre du sujet ne peut pas être vide.");
            return;
        }

        if (sujet.getSujet() == null || sujet.getSujet().isEmpty()) {
            System.out.println("Erreur : Le contenu du sujet ne peut pas être vide.");
            return;
        }

        String query = "INSERT INTO sujet (titre, sujet, id_user) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, sujet.getTitre());
            pstmt.setString(2, sujet.getSujet());
            pstmt.setInt(3, sujet.getId_user().getId()); // Assurez-vous que User a une méthode getId()
            pstmt.executeUpdate();
            System.out.println("Sujet ajouté après validation : " + sujet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Sujet sujet) {
        String query = "UPDATE sujet SET titre = ?, sujet = ? WHERE id_sujet = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, sujet.getTitre());
            pstmt.setString(2, sujet.getSujet());
            pstmt.setInt(3, sujet.getId_suj());
            pstmt.executeUpdate();
            System.out.println("Sujet mis à jour : " + sujet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Sujet sujet) {
        try {
            // Suppression en cascade des commentaires associés
            deleteComments(sujet.getId_suj()); // Méthode pour supprimer les commentaires associés

            // Suppression du sujet lui-même
            String query = "DELETE FROM sujet WHERE id_sujet = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, sujet.getId_suj());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Sujet supprimé avec succès.");
                } else {
                    System.out.println("Erreur : Aucun sujet trouvé avec cet ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteComments(int idSujet) throws SQLException {
        // Supprimer les commentaires associés au sujet spécifié
        String query = "DELETE FROM commentaire WHERE id_sujet = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idSujet);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Nombre de commentaires supprimés : " + affectedRows);
        }
    }


    @Override
    public List<Sujet> getAll() {
        List<Sujet> sujets = new ArrayList<>();
        String query = "SELECT * FROM sujet";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_suj = rs.getInt("id_sujet");
                String titre = rs.getString("titre");
                String contenu = rs.getString("sujet");
                int id_user = rs.getInt("id_user");
                // Assurez-vous d'avoir une classe User avec un constructeur approprié
                User user = new User(id_user);
                Sujet s = new Sujet(id_suj, titre, contenu, user);
                sujets.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sujets;
    }

    @Override
    public List<Sujet> getbyfilter() {
        List<Sujet> sujets = new ArrayList<>();
        String query = "SELECT * FROM sujet ORDER BY titre ASC";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_suj = rs.getInt("id_sujet");
                String titre = rs.getString("titre");
                String contenu = rs.getString("sujet");
                int id_user = rs.getInt("id_user");
                // Assurez-vous d'avoir une classe User avec un constructeur approprié
                User user = new User(id_user);
                Sujet s = new Sujet(id_suj, titre, contenu, user);
                sujets.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sujets;
    }

    @Override
    public List<Sujet> getbyid() {
        List<Sujet> sujets = new ArrayList<>();
        String query = "SELECT * FROM sujet WHERE id_sujet = ?";

        // Pour cet exemple, nous fixons un ID spécifique, par exemple 1
        int id = 140;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_suj = rs.getInt("id_sujet");
                String titre = rs.getString("titre");
                String contenu = rs.getString("sujet");
                int id_user = rs.getInt("id_user");

                User user = new User(id_user); // Assuming User class has a constructor that accepts ID
                Sujet s = new Sujet(id_suj, titre, contenu, user);
                sujets.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sujets;
    }
}



