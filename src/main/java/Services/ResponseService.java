package Services;

import Models.ErrorCategory;
import Models.Reclamation;
import Models.Response;
import Models.Status;
import Utils.Myconnection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import Interfaces.IResponseService;

public class ResponseService implements IResponseService {
    private Connection cnx= Myconnection.getInstance().getCnx();

    public void ajouter(Response response) {
        try {
            String req = "INSERT INTO response(reclamation_id, message,admin_id) VALUES (?, ?, ?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, response.getReclamation().getId());
            pst.setString(2, response.getMessage());
            pst.setInt(3,response.getAdminId());
            pst.executeUpdate();
            System.out.println("Response added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Response> afficher() {
        List<Response> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM response";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt("id");
                Reclamation reclamation = getReclamationById(rst.getInt("reclamation_id"));
                String message = rst.getString("message");
                int adminId = rst.getInt("admin_id");
                list.add(new Response(id, reclamation, message,adminId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Response getResponseById(int id) {
        Response response = null;
        try {
            String req = "SELECT * FROM response WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                Reclamation reclamation = getReclamationById(rst.getInt("reclamation_id"));
                String message = rst.getString("message");
                int adminId = rst.getInt("admin_id ");
                response = new Response(id, reclamation, message,adminId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public void modifier(int id,Response response) {
        try {
            String req = "UPDATE response SET reclamation_id=?, message=?, admin_id WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, response.getReclamation().getId());
            pst.setString(2, response.getMessage());
            pst.setInt(3,response.getAdminId());
            pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Response updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM response WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Response deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Reclamation getReclamationById(int RecID) {
        Reclamation r = null;
        try {
            String req = "SELECT * FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, RecID);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                int id = rst.getInt("id");
                String title = rst.getString("title");
                String description = rst.getString("description");
                ErrorCategory errorCategory = getErrorCategoryById(rst.getInt("errorCategoryId"));
                Status status = Status.valueOf(rst.getString("status"));
                String createdAt = rst.getTimestamp("creationDate").toString();
                List<String> attachments = Arrays.asList(rst.getString("attachments").split(","));
                int userId = rst.getInt("user_id");
                r = new Reclamation(id, title,description, errorCategory, status, attachments, userId, createdAt);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    public ErrorCategory getErrorCategoryById(int id) {
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
    public void sendEmail(int userId, String message) {
        String userEmail = getUserEmailById(userId);
        System.out.println("user mail  : "+ userEmail);
        String subject = "Response to Your Reclamation";
        String body = "Dear User,\n\nYour reclamation has received a response:\n\n" + message + "\n\nBest regards,\nMoneyMinder";

        final String username = "itssaminechebbi@gmail.com";
        final String password = "pzbpaomxngxqcpbe";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("oryxoneticketing@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserEmailById(int userId) {
        String email = "";
        try {
            String req = "SELECT mail FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, userId);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                email = rst.getString("mail");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return email;
    }

}
