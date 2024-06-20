package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.Objectif;
import Utils.Myconnection;

public class ObjectifService {

    private Connection connection;

    public ObjectifService() {
        connection = Myconnection.getInstance().getCnx(); // Obtenez votre connexion à la base de données ici
    }

    public void add(Objectif objectif) {
        String query = "INSERT INTO objectifs (titre, montant_globale, echeance, mois, commentaire) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, objectif.getTitre());
            preparedStatement.setDouble(2, objectif.getMontant_globale());
            preparedStatement.setDouble(3, objectif.getEcheance());
            preparedStatement.setInt(4, objectif.getMois());
            preparedStatement.setString(5, objectif.getCommentaire());

            preparedStatement.executeUpdate();
            System.out.println("Objectif ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
