package Services;

import Interfaces.IReclamationService;
import Models.ErrorCategory;
import Models.Reclamation;
import Models.Status;
import Models.User;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Properties;

public class ReclamationService implements IReclamationService {
    private Connection cnx= Myconnection.getInstance().getCnx();

    public void ajouter(Reclamation r) {
        try {
            String req = "INSERT INTO reclamation(description, errorCategoryId, status, creationDate, attachments, user_id,title) VALUES (?, ?, ?, current_timestamp(), ?, ?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, r.getDescription());
            pst.setInt(2, r.getErrorCategory().getId());
            pst.setString(3, r.getStatus().name());
            pst.setString(4, String.join(",", r.getAttachments()));
            pst.setInt(5, r.getUserId());
            pst.setString(6,r.getTitle());
            pst.executeUpdate();
            System.out.println("Reclamation added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt("id");
                String title = rst.getString("title");
                String description = rst.getString("description");
                ErrorCategory errorCategory = getErrorCategoryById(rst.getInt("errorCategoryId"));
                Status status = Status.valueOf(rst.getString("status"));
//                Timestamp timestamp = rst.getTimestamp("creationDate");
                List<String> attachments = Arrays.asList(rst.getString("attachments").split(","));
                String createAt = rst.getString("creationDate");
                int userId = rst.getInt("user_id");
                list.add(new Reclamation( id,title,description, errorCategory, status,attachments,userId,createAt));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Reclamation getReclamationById(int id) {
        Reclamation r = null;
        try {
            String req = "SELECT * FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                String description = rst.getString("description");
                String title = rst.getString("title");
                ErrorCategory errorCategory = getErrorCategoryById(rst.getInt("errorCategoryId"));
                Status status = Status.valueOf(rst.getString("status"));
//                Timestamp timestamp = rst.getTimestamp("creationDate");
                List<String> attachments = Arrays.asList(rst.getString("attachments").split(","));
                String createAt = rst.getString("creationDate");
                int userId = rst.getInt("user_id");
                r = new Reclamation( id,title,description, errorCategory, status,attachments,userId,createAt);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    public void modifier(int id,Reclamation r) {
        try {
            String req = "UPDATE reclamation SET description=?, errorCategoryId=?, status=?, attachments=?,user_id=?,title=? WHERE id=?";

            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, r.getDescription());
            pst.setInt(2, r.getErrorCategory().getId());
            pst.setString(3, r.getStatus().name());
            pst.setString(4, String.join(",", r.getAttachments()));
            pst.setInt(5, r.getUserId());
            pst.setString(6, r.getTitle());
            pst.setInt(7, id);
            System.out.println(req);
            pst.executeUpdate();
            System.out.println("Reclamation updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req1 = "DELETE FROM response WHERE reclamation_id=?;";
            String req2 ="DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst1.setInt(1, id);
            pst2.setInt(1, id);

            pst1.executeUpdate();
            pst2.executeUpdate();
            System.out.println("Reclamation deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ErrorCategory getErrorCategoryById(int id) {
        ErrorCategory ec = null;
        try {
            String req = "SELECT * FROM errorcategory WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                String name = rst.getString("name");
                ec = new ErrorCategory(id, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ec;
    }
    public List<Reclamation> getReclamationsByUserId(int userId) {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE user_id = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, userId);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt("id");
                String title = rst.getString("title");
                String description = rst.getString("description");
                ErrorCategory errorCategory = getErrorCategoryById(rst.getInt("errorCategoryId"));
                Status status = Status.valueOf(rst.getString("status"));
                List<String> attachments = Arrays.asList(rst.getString("attachments").split(","));
                String creationDate = rst.getString("creationDate");
                list.add(new Reclamation(id, title,description, errorCategory, status, attachments, userId, creationDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void sendEmail(Reclamation reclamation) {
        String userEmail = "fincompare@gmail.com";
        UserServices userService = new UserServices();
        User user = userService.getUserById(reclamation.getUserId());
        String subject = "Response to Your Reclamation";
        String body = "Dear Admin,\n\n" +
                "Your have been received a reclamation with title '" + reclamation.getTitle() + "\n\n" +

                "Details of the reclamation:\n" +
                "Title: " + reclamation.getTitle() + "\n" +
                "Description: " + reclamation.getDescription() + "\n" +
                "Error Category: " + reclamation.getErrorCategory().getName() + "\n" +
                "Status: " + reclamation.getStatus() + "\n" +
                "Creation Date: " + reclamation.getCreationDate() + "\n" +
                "Attachments: " + String.join(", ", reclamation.getAttachments()) + "\n\n" +
                "Best regards,\n" +
                "MoneyMinder";

        final String username = "itssaminechebbi@gmail.com";
        final String password = "pzbpaomxngxqcpbe";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("your-email@example.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
