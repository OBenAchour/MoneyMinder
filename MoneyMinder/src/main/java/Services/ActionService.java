package Services;


import Models.Action;
import Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionService {

    Connection connection;
    public ActionService() {
        connection = MyConnexion.getInstance().getCnx();
    }

    public void processActions(List<Action> actions) {

        PreparedStatement insertStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;

        try {



            // Prepare statements for insert and update
            String insertQuery = "INSERT INTO action (nom, cours, prix_achat, prix_vente, quantite, ouverture, cloture, prix_haut, prix_bas, variation, quantite_echange) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String updateQuery = "UPDATE action SET cours = ?, prix_achat = ?, prix_vente = ?, quantite = ?, ouverture = ?, cloture = ?, prix_haut = ?, prix_bas = ?, variation = ?, quantite_echange = ? WHERE nom = ?";

            insertStatement = connection.prepareStatement(insertQuery);
            updateStatement = connection.prepareStatement(updateQuery);

            // Iterate through actions
            for (Action action : actions) {
                // Check if action exists by nom
                boolean exists = false;

                updateStatement.clearParameters();
                updateStatement.setFloat(1, action.getCours());
                updateStatement.setFloat(2, action.getPrix_achat());
                updateStatement.setFloat(3, action.getPrix_vente());
                updateStatement.setInt(4, action.getQuantite());
                updateStatement.setFloat(5, action.getOuverture());
                updateStatement.setFloat(6, action.getCloture());
                updateStatement.setFloat(7, action.getPrix_haut());
                updateStatement.setFloat(8, action.getPrix_bas());
                updateStatement.setFloat(9, action.getVariation());
                updateStatement.setFloat(10, action.getQuantite_echange());
                updateStatement.setString(11, action.getNom());

                // Execute update and check result count
                int updatedRows = updateStatement.executeUpdate();
                if (updatedRows > 0) {
                    exists = true;
                }

                if (!exists) {
                    // Insert action
                    insertStatement.clearParameters();
                    insertStatement.setString(1, action.getNom());
                    insertStatement.setFloat(2, action.getCours());
                    insertStatement.setFloat(3, action.getPrix_achat());
                    insertStatement.setFloat(4, action.getPrix_vente());
                    insertStatement.setInt(5, action.getQuantite());
                    insertStatement.setFloat(6, action.getOuverture());
                    insertStatement.setFloat(7, action.getCloture());
                    insertStatement.setFloat(8, action.getPrix_haut());
                    insertStatement.setFloat(9, action.getPrix_bas());
                    insertStatement.setFloat(10, action.getVariation());
                    insertStatement.setFloat(11, action.getQuantite_echange());

                    insertStatement.addBatch();
                }
            }

            // Execute batch insert
            insertStatement.executeBatch();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction on error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }
    public void addActions(List<Action> actions) {
        String query = "INSERT INTO action ( nom, cours, prix_achat, prix_vente, quantite, ouverture, cloture, prix_haut, prix_bas, variation, quantite_echange) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (Action action : actions) {
                statement.setString(1, action.getNom());
                statement.setFloat(2, action.getCours());
                statement.setFloat(3, action.getPrix_achat());
                statement.setFloat(4, action.getPrix_vente());
                statement.setInt(5, action.getQuantite());
                statement.setFloat(6, action.getOuverture());
                statement.setFloat(7, action.getCloture());
                statement.setFloat(8, action.getPrix_haut());
                statement.setFloat(9, action.getPrix_bas());
                statement.setFloat(10, action.getVariation());
                statement.setFloat(11, action.getQuantite_echange());

                statement.addBatch();
            }

            int[] results = statement.executeBatch();
            // Handle results if needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Create
    public void addAction(Action action) throws SQLException {
        String query = "INSERT INTO action (nom, cours, prix_achat, prix_vente, quantite, ouverture, cloture, prix_haut, prix_bas, variation, quantite_echange) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, action.getNom());
            statement.setFloat(2, action.getCours());
            statement.setFloat(3, action.getPrix_achat());
            statement.setFloat(4, action.getPrix_vente());
            statement.setInt(5, action.getQuantite());
            statement.setFloat(6, action.getOuverture());
            statement.setFloat(7, action.getCloture());
            statement.setFloat(8, action.getPrix_haut());
            statement.setFloat(9, action.getPrix_bas());
            statement.setFloat(10, action.getVariation());
            statement.setFloat(11, action.getQuantite_echange());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    // Read
    public Action getAction(int id) throws SQLException {
        String query = "SELECT * FROM action WHERE id_action = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Action(resultSet.getInt("id_action"), resultSet.getString("nom"), resultSet.getFloat("cours"), resultSet.getFloat("prix_achat"), resultSet.getFloat("prix_vente"), resultSet.getInt("quantite"), resultSet.getFloat("ouverture"), resultSet.getFloat("cloture"), resultSet.getFloat("prix_haut"), resultSet.getFloat("prix_bas"), resultSet.getFloat("variation"), resultSet.getFloat("quantite_echange"));
                }
            }
        }
        return null;
    }

    // Update
    public void updateAction(Action action) throws SQLException {
        String query = "UPDATE action SET nom = ?, cours = ?, prix_achat = ?, prix_vente = ?, quantite = ?, ouverture = ?, cloture = ?, prix_haut = ?, prix_bas = ?, variation = ?, quantite_echange = ? WHERE id_action = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, action.getNom());
            statement.setFloat(2, action.getCours());
            statement.setFloat(3, action.getPrix_achat());
            statement.setFloat(4, action.getPrix_vente());
            statement.setInt(5, action.getQuantite());
            statement.setFloat(6, action.getOuverture());
            statement.setFloat(7, action.getCloture());
            statement.setFloat(8, action.getPrix_haut());
            statement.setFloat(9, action.getPrix_bas());
            statement.setFloat(10, action.getVariation());
            statement.setFloat(11, action.getQuantite_echange());
            statement.setInt(12, action.getId_action());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteAction(int id) throws SQLException {
        String query = "DELETE FROM action WHERE id_action = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // List all actions
    public List<Action> getAllActions() throws SQLException {
        List<Action> actions = new ArrayList<>();
        String query = "SELECT * FROM action";
        try ( PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Action action = new Action(resultSet.getInt("id_action"), resultSet.getString("nom"), resultSet.getFloat("cours"), resultSet.getFloat("prix_achat"), resultSet.getFloat("prix_vente"), resultSet.getInt("quantite"), resultSet.getFloat("ouverture"), resultSet.getFloat("cloture"), resultSet.getFloat("prix_haut"), resultSet.getFloat("prix_bas"), resultSet.getFloat("variation"), resultSet.getFloat("quantite_echange"));
                actions.add(action);
            }
        }
        return actions;
    }

    public Action getActionByNom(String nom) {
        String query = "SELECT * FROM action WHERE nom = ?";
        Action action = null;

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                action = new Action(resultSet.getInt("id_action"), resultSet.getString("nom"), resultSet.getFloat("cours"), resultSet.getFloat("prix_achat"), resultSet.getFloat("prix_vente"), resultSet.getInt("quantite"), resultSet.getFloat("ouverture"), resultSet.getFloat("cloture"), resultSet.getFloat("prix_haut"), resultSet.getFloat("prix_bas"), resultSet.getFloat("variation"), resultSet.getFloat("quantite_echange"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action;
    }
}
