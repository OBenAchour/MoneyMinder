package Services;

import Models.Compte;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteService {
    private Connection connection;

    public CompteService() {
        this.connection = Myconnection.getInstance().getCnx();
    }


    // Read an account by ID
    public Compte getCompteById(int id) throws SQLException {
        String query = "SELECT * FROM compte WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToCompte(resultSet);
            } else {
                return null;
            }
        }
    }

    // Update an account
    public void updateCompte(Compte compte) throws SQLException {
        String query = "UPDATE compte SET solde = ?  WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, compte.getSolde());
            statement.setInt(2, compte.getId_user());
            statement.executeUpdate();
        }
    }




    // Helper method to map a ResultSet row to a Compte object
    private Compte mapRowToCompte(ResultSet resultSet) throws SQLException {
        Compte compte = new Compte();
        compte.setId(resultSet.getInt("id"));
        compte.setSolde(resultSet.getFloat("solde"));
        compte.setId_user(resultSet.getInt("id_user"));
        return compte;
    }
}
