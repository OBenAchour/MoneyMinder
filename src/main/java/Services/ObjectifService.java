package Services;

import Interfaces.InterfaceMoneyMinder;
import Models.Objectif;
import Models.Catobj;
import Models.Wallet;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectifService implements InterfaceMoneyMinder<Objectif> {

    private Connection connectDB;
    private SMSService smsService;

    public ObjectifService() {
        Myconnection connectNow = Myconnection.getInstance();
        connectDB = connectNow.getCnx();
        smsService = new SMSService();
    }

    @Override
    public void add(Objectif objectif) {
        String insertQueryObjectif = "INSERT INTO `objectif`( `titre`, `montant_globale`, `echeance`, `mois`, `annee`, `commentaire`, `id_cat_obj`) VALUES (?,?,?,?,?,?,?)";

        try {
            System.out.println(objectif.toString());
            PreparedStatement ps = connectDB.prepareStatement(insertQueryObjectif);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
            ps.setDouble(3, this.calculerEcheance(objectif.getMontant_globale(),objectif.getMois()));
            ps.setInt(4, objectif.getMois());
            ps.setInt(5, 2024);
            ps.setString(6, objectif.getCommentaire());
//            ps.setInt(5, objectif.getCatobj().getId_obj());
            if (objectif.getCatobj() != null) {
                ps.setInt(7, objectif.getCatobj().getId_obj());
            } else {
                System.out.println("i null");
                ps.setNull(7, 1);
            }
//            ps.setInt(6, objectif.getId_wallet());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'ajout de l'objectif", e);
        }
    }

    @Override
    public void delete(Objectif objectif) {
        String query = "DELETE FROM objectif WHERE id = ?";
        try (PreparedStatement statement = connectDB.prepareStatement(query)) {
            statement.setInt(1, objectif.getId_obj());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Objectif objectif) {
        String req = "UPDATE `objectif` SET `titre` = ?, `montant_globale` = ?, `mois` = ?, `commentaire` = ?, `id_cat_obj` = ?, `montant_conservé`= ?  WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
            ps.setInt(3, objectif.getMois());
            ps.setString(4, objectif.getCommentaire());
            ps.setInt(5, objectif.getCatobj().getId_obj());
            ps.setDouble(6, objectif.getMontant_conservé());
            ps.setInt(7, objectif.getId_obj()); // L'ID doit être défini pour identifier l'objectif à mettre à jour

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'objectif", e);
        }
    }


    @Override
    public List<Objectif> getAll() {
        List<Objectif> objectifs = new ArrayList<>();
        String query = "SELECT o.*, c.catobj, c.idObj FROM objectif o LEFT JOIN categorieobj c ON o.id_cat_obj = c.idObj";
        try {
            System.out.println("Executing query: " + query); // Debug statement
            PreparedStatement ps = connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ResultSet row: id = " + rs.getInt("id")); // Debug statement

                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                Catobj catobj = new Catobj();
                catobj.setId_obj(rs.getInt("idObj")); // Corrected to "idObj"
                catobj.setCatobj(rs.getString("catobj")); // Set category name
                objectif.setCatobj(catobj);

                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()); // More detailed error logging
            throw new RuntimeException("Erreur lors de la récupération des objectifs", e);
        }
        return objectifs;
    }


    @Override
    public List<Objectif> getbyfilter(String column, String value) {
        List<Objectif> filteredObjectifs = new ArrayList<>();
        String req = "SELECT * FROM `objectif` WHERE " + column + " = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                // Fetch Catobj here and set it to objectif
                Catobj catobj = new Catobj();
                catobj.setId_obj(rs.getInt("id_cat_obj"));
                objectif.setCatobj(catobj);

                filteredObjectifs.add(objectif);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des objectifs filtrés", e);
        }
        return filteredObjectifs;
    }

    @Override
    public List<Objectif> getbyid(int id) {
        List<Objectif> objectifs = new ArrayList<>();
        String query = "SELECT * FROM `objectif` WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Objectif objectif = new Objectif();
                objectif.setId_obj(rs.getInt("id"));
                objectif.setTitre(rs.getString("titre"));
                objectif.setMontant_globale(rs.getDouble("montant_globale"));
                objectif.setMois(rs.getInt("mois"));
                objectif.setCommentaire(rs.getString("commentaire"));

                // Fetch Catobj here and set it to objectif
                Catobj catobj = new Catobj();
                catobj.setId_obj(rs.getInt("id_cat_obj"));
                objectif.setCatobj(catobj);

                objectifs.add(objectif);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'objectif par ID", e);
        }
        return objectifs;
    }


    public void calculerEcheance() {
        List<Objectif> objectifs = getAll();
        for (Objectif objectif : objectifs) {
            int dureeTotaleMois = objectif.getMois();
            double echeance = objectif.getMontant_globale() / dureeTotaleMois;
            objectif.setEcheance(echeance);
            update(objectif);
            System.out.println("L'échéance pour l'objectif '" + objectif.getTitre() + "' est de : " + echeance);
        }
    }

    public double calculerEcheance(double M_Total,int mois) {
        return M_Total/mois;
    }

    public void checkProgressAndSendSMS(Objectif objectif, double progressPercentage, String phoneNumber) {
        String category = objectif.getCatobj().getCatobj();
        double targetPercentage = 0;

        switch (category) {
            case "immobilier":
            case "véhicule":
                targetPercentage = 10;
                break;
            case "voyage":
                targetPercentage = 25;
                break;
            default:
                if (objectif.getMontant_globale() > 20000) {
                    targetPercentage = 10;
                } else {
                    targetPercentage = 25;
                }
                break;
        }

        double step = targetPercentage / 100.0;
        double totalAmount = objectif.getMontant_globale();
        double currentAmount = totalAmount * (progressPercentage / 100.0);

        if (currentAmount >= step * totalAmount) {
            // smsService.sendSMS(phoneNumber, "Félicitations ! Vous avez atteint " + progressPercentage + "% de votre objectif \"" + objectif.getTitre() + "\".");
        }
    }

//    public void calculerPourcentageObjectifsParRapportAuWallet(Wallet wallet) {
//        List<Objectif> objectifs = getAll();
//        double montantTotalObjectifs = 0.0;
//
//        for (Objectif objectif : objectifs) {
//            montantTotalObjectifs += objectif.getMontant_globale();
//        }
//
//        double pourcentageTotalObjectifs = (montantTotalObjectifs / wallet.getMontant()) * 100;
//        System.out.println("Le pourcentage total des montants des objectifs par rapport au wallet est de : " + pourcentageTotalObjectifs + "%");
//
//        for (Objectif objectif : objectifs) {
//            double pourcentageObjectif = (objectif.getMontant_globale() / montantTotalObjectifs) * 100;
//            double montantParPourcentage = (pourcentageObjectif / 100) * wallet.getMontant();
//
//            System.out.println("L'objectif '" + objectif.getTitre() + "' représente " + pourcentageObjectif + "% du total des objectifs.");
//            System.out.println("Montant calculé pour l'objectif '" + objectif.getTitre() + "' est : " + montantParPourcentage);
//
//            if (montantParPourcentage >= objectif.getMontant_globale()) {
//                System.out.println("Le montant calculé est supérieur ou égal au montant de l'objectif sélectionné.");
//            } else {
//                System.out.println("Le montant calculé est inférieur au montant de l'objectif sélectionné.");
//            }
//        }
//    }

//    public void updateMontantConserve(int objectifId, double nouveauMontant) {
//        // Récupérer l'objectif par son ID
//        Objectif objectif = (Objectif) getbyid(objectifId);
//
//        if (objectif != null) {
//            // Ajouter le nouveau montant au montant conservé actuel
//            double montantConserveActuel = objectif.getMontant_conservé();
//            objectif.setMontant_conservé(montantConserveActuel + nouveauMontant);
//
//            // Mettre à jour l'objectif dans la base de données
//            // Cela dépend de la façon dont vous gérez la persistance des données
//            // Par exemple, en utilisant un DAO ou directement une mise à jour SQL
//            update(objectif);
//        }
//    }
}
