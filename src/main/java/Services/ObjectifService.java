package Services;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Interfaces.InterfaceMoneyMinder;
import Models.Objectif;
import Models.Catobj;
import Models.Wallet;
import Utils.Myconnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectifService implements InterfaceMoneyMinder<Objectif> {


    private static final String ACCOUNT_SID = "AC3788dff27b6661da6ca889608c31c6ad";
    private static final String AUTH_TOKEN = "d21bc4cfe5a45eace407599c14e1b920";
    private static final String FROM_PHONE_NUMBER = "+16184089413";



    private Connection connectDB;
//    private SMSService smsService;
private Map<Integer, Double> lastNotifiedPercentages = new HashMap<>();


    public ObjectifService() {
        Myconnection connectNow = Myconnection.getInstance();
        connectDB = connectNow.getCnx();
//        smsService = new SMSService();
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

            if (objectif.getCatobj() != null) {
                ps.setInt(7, objectif.getCatobj().getId_obj());
            } else {
                System.out.println("i null");
                ps.setNull(7, 1);
            }

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
        String req = "UPDATE `objectif` SET `titre` = ?, `montant_globale` = ?, `mois` = ?, `commentaire` = ?, `id_cat_obj` = ?, `montant_conserve`= ?  WHERE `id` = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(req);
            ps.setString(1, objectif.getTitre());
            ps.setDouble(2, objectif.getMontant_globale());
            ps.setInt(3, objectif.getMois());
            ps.setString(4, objectif.getCommentaire());
            ps.setInt(5, objectif.getCatobj().getId_obj());
            ps.setDouble(6, objectif.getMontant_conserve());
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
                objectif.setMontant_conserve(rs.getDouble("montant_conserve"));

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
                objectif.setMontant_conserve(rs.getDouble("montant_conserve"));

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
                objectif.setMontant_conserve(rs.getDouble("montant_conserve"));


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



    public double calculerEcheance(double M_Total,int mois) {
        return M_Total/mois;
    }


    public void checkProgressAndSendSMS(Objectif objectif, String phoneNumber) {
        double totalAmount = objectif.getMontant_globale();
        double currentAmount = objectif.getMontant_conserve();
        double percentage = (currentAmount / totalAmount) * 100;

        double threshold = totalAmount > 50000 ? 10 : 25;
        double lastNotifiedPercentage = lastNotifiedPercentages.getOrDefault(objectif.getId_obj(), 0.0);

        if (percentage >= lastNotifiedPercentage + threshold) {
            String messageBody = "Félicitations ! Vous avez atteint " + ((int) (percentage / threshold) * threshold) + "% de votre objectif \"" + objectif.getTitre() + "\".";
            sendSMS(phoneNumber, messageBody);
            lastNotifiedPercentages.put(objectif.getId_obj(), (int) (percentage / threshold) * threshold);
        }
    }

    public void sendSMS(String toPhoneNumb, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumb),
                new PhoneNumber(FROM_PHONE_NUMBER),
                messageBody
        ).create();
       // System.out.println("SMS sent: " + message.getSid());
    }



}



