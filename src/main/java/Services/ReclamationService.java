package services;

import entities.ErrorCategory;
import entities.Reclamation;
import entities.Status;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReclamationService implements IReclamationService {
    private Connection cnx = DataSource.getinstance().getCnx();

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
//            DELETE FROM response WHERE reclamation.id=15;
//            DELETE FROM reclamation WHERE id=15;
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

}
