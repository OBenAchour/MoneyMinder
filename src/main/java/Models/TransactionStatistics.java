package Models;

import Models.Transaction;
import Utils.Myconnection;

import java.sql.*;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionStatistics {

    Connection cnx= Myconnection.getInstance().getCnx();



    public Map<String, Double> getTotalexpenseByCategory(int type, int userId,int mois,int annee) {

            Map<String, Double> categoryTotals = new HashMap<>();
            Connection cnx = Myconnection.getInstance().getCnx();

            try (PreparedStatement preparedStatement = cnx.prepareStatement(
                    "SELECT c.catDep AS Catégorie, SUM(t.montant) AS total " +
                            "FROM transactions t " +
                            "JOIN categoriedep c " +
                            "ON t.id_cat_depense = c.idCatDep " +
                            "WHERE t.type = ? AND t.id_user = ?  AND t.mois = ? AND t.annee= ? " +
                            "GROUP BY c.idCatDep " +
                            "LIMIT 0, 25")) {

                preparedStatement.setInt(1, type);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, mois);
                preparedStatement.setInt(4, annee);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String category = resultSet.getString("Catégorie");
                        double total = resultSet.getDouble("total");
                        categoryTotals.put(category, total);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categoryTotals;
        }


    public Map<String, Double> getTotalincomeByCategory(int type, int userId,int mois,int annee) {

        Map<String, Double> categoryTotals = new HashMap<>();
        Connection cnx = Myconnection.getInstance().getCnx();

        try (PreparedStatement preparedStatement = cnx.prepareStatement(
                "SELECT c.catRev AS Catégorie, SUM(t.montant) AS total " +
                        "FROM transactions t " +
                        "JOIN categorierev c " +
                        "ON t.id_cat_revenu = c.idCatRev " +
                        "WHERE t.type = ? AND t.id_user = ?  AND t.mois = ? AND t.annee= ? " +
                        "GROUP BY c.idCatRev " +
                        "LIMIT 0, 25")) {

            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, mois);
            preparedStatement.setInt(4, annee);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String category = resultSet.getString("Catégorie");
                    double total = resultSet.getDouble("total");
                    categoryTotals.put(category, total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryTotals;
    }

    public Float getTotalincomeoutcomeyearly(int type, int userId,int annee) {
        Connection cnx = Myconnection.getInstance().getCnx();
        Float totalexpence = 0f;

        try (PreparedStatement preparedStatement = cnx.prepareStatement(
                "SELECT SUM(t.montant) AS total " +
                        "FROM transactions t " +
                        "WHERE t.type =? AND t.id_user =?  AND t.annee =? ")) {

            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, annee);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalexpence = resultSet.getFloat("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalexpence;
    }

    public Float getTotalincomeoutcomemonthly(int type, int userId,int annee,int mois) {
        Connection cnx = Myconnection.getInstance().getCnx();
        Float totalexpence = 0f;

        try (PreparedStatement preparedStatement = cnx.prepareStatement(
                "SELECT SUM(t.montant) AS total " +
                        "FROM transactions t " +
                        "WHERE t.type =? AND t.id_user =?  AND t.annee =? AND t.mois =? " )) {
            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, annee);
            preparedStatement.setInt(4, mois);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalexpence = resultSet.getFloat("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalexpence;
    }
    public String getMonthName(int month) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return monthNames[month - 1];
    }

}
