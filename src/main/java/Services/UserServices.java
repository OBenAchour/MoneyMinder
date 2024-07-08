package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.User;
import Utils.Myconnection;
import Utils.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServices implements InterfaceMoneyMinder<User> {

    private final Connection cnx;

    // Constantes pour les requêtes SQL
    private static final String INSERT_USER = "INSERT INTO `user`(`nom`, `prenom`, `Date_naissance`, `tel`, `mail`, `Mot_de_passe`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM `user` WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE `user` SET `nom` = ?, `prenom` = ?, `Date_naissance` = ?, `tel` = ?, `mail` = ?, `Mot_de_passe` = ? WHERE `id` = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM `user`";
    private static final String SELECT_USER_BY_FILTER = "SELECT * FROM `user` ";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";

    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM `user` WHERE mail = ?";
    private static final String LOGIN_USER = "SELECT * FROM `user` WHERE mail = ? AND Mot_de_passe = ?";

    private static final String UPDATE_PASSWORD = "UPDATE `user` SET `Mot_de_passe` = ? WHERE `mail` = ?";

    private static final String EMAIL_EXISTS = "SELECT * FROM `user` WHERE `mail` = ?";


    public UserServices() {
        this.cnx = Myconnection.getInstance().getCnx();
    }

    //    @Override
//    public void add(User user) {
//        try (PreparedStatement ps = cnx.prepareStatement(INSERT_USER)) {
//            ps.setString(1, user.getNom());
//            ps.setString(2, user.getPrenom());
//            ps.setDate(3, user.getDate_de_naiss());
//            ps.setInt(4, user.getTel());
//            ps.setString(5, user.getMail());
//            ps.setString(6, user.getMot_de_passe());
//            ps.executeUpdate();
//            System.out.println("Utilisateur ajouté avec succès.");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
//        }
//    }
    @Override
    public void add(User user) {
        try (PreparedStatement ps = cnx.prepareStatement(INSERT_USER)) {
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setDate(3, user.getDate_de_naiss());
            ps.setInt(4, user.getTel());
            ps.setString(5, user.getMail());
            ps.setString(6, user.getMot_de_passe());
            ps.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
        }
    }
    public User getUserById(int id) {
        User u = null;
        try {
            String req = "SELECT * FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                String nom = rst.getString("nom");
                String prenom = rst.getString("prenom");
                String mot_de_passe = rst.getString("mot_de_passe");
                java.sql.Date date_de_naiss = rst.getDate("Date_naissance");
                String mail = rst.getString("mail");
                u = new User(id, nom, prenom, mot_de_passe, new java.util.Date(date_de_naiss.getTime()), mail);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }


    public boolean emailExists(String email) throws SQLException {
        try (PreparedStatement ps = cnx.prepareStatement(EMAIL_EXISTS)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement ps = cnx.prepareStatement(DELETE_USER)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur: " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement ps = cnx.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setDate(3, user.getDate_de_naiss());
            ps.setInt(4, user.getTel());
            ps.setString(5, user.getMail());
            ps.setString(6, user.getMot_de_passe());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
            System.out.println("Utilisateur mis à jour avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(SELECT_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setDate_de_naiss(rs.getDate("Date_naissance"));
                user.setTel(rs.getInt("tel"));
                user.setMail(rs.getString("mail"));
                user.setMot_de_passe(rs.getString("Mot_de_passe"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getbyfilter(String request) {
        List<User> users = new ArrayList<>();
        String req ="Select * from `user`"+request;
        try { Statement st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setDate_de_naiss(rs.getDate("Date_naissance"));
                    user.setTel(rs.getInt("tel"));
                    user.setMail(rs.getString("mail"));
                    user.setMot_de_passe(rs.getString("Mot_de_passe"));
                    users.add(user);
                }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs par filtre: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getbyid() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = cnx.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setString(1, "%filterName%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setDate_de_naiss(rs.getDate("Date_naissance"));
                    user.setTel(rs.getInt("tel"));
                    user.setMail(rs.getString("mail"));
                    user.setMot_de_passe(rs.getString("Mot_de_passe"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs par ID: " + e.getMessage());
        }
        return users;
    }


    public int login(String email, String motDePasse) throws SQLException {
        try (PreparedStatement statement = cnx.prepareStatement(LOGIN_USER)) {
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserSession.setEmail(email); // Stocke l'email de l'utilisateur connecté
                return resultSet.getInt("id");
            } else {
                return -1; // Aucun utilisateur correspondant aux informations d'identification
            }
        }
    }


    public void updatePassword(String email, String newPassword) throws SQLException {
        try (PreparedStatement ps = cnx.prepareStatement(UPDATE_PASSWORD)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully for user with email: " + email);
            } else {
                throw new SQLException("Failed to update password. User not found with email: " + email);
            }
        } catch (SQLException e) {
            throw new SQLException("Error updating password: " + e.getMessage());
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        try (PreparedStatement ps = cnx.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setDate_de_naiss(rs.getDate("Date_naissance"));
                    user.setTel(rs.getInt("tel"));
                    user.setMail(rs.getString("mail"));
                    user.setMot_de_passe(rs.getString("Mot_de_passe"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user by email: " + e.getMessage());
            throw e;
        }
        return user;
    }

//    public void updateUser(User user) throws SQLException {
//        String query = "UPDATE users SET nom = ?, prenom = ?, date_de_naiss = ?, tel = ?, mail = ? WHERE id = ?";
//
//        try (Connection connection = Database.getConnection();  // Assurez-vous que vous avez une méthode pour obtenir la connexion à la base de données
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, user.getNom());
//            statement.setString(2, user.getPrenom());
//            statement.setString(3, user.getDate_de_naiss());
//            statement.setString(4, user.getTel());
//            statement.setString(5, user.getMail());
//            statement.setInt(6, user.getId());
//
//            statement.executeUpdate();
//        }
//    }

}
