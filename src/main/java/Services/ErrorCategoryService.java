package Services;

import Interfaces.IErrorCategoryService;
import Models.ErrorCategory;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ErrorCategoryService implements IErrorCategoryService {
    private Connection cnx= Myconnection.getInstance().getCnx();
    public void ajouter(ErrorCategory ec) {
        try {
            String req = "INSERT INTO errorcategory(name) VALUES (?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, ec.getName());
            pst.executeUpdate();
            System.out.println("Error Category added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ErrorCategory> afficher() {
        List<ErrorCategory> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM errorcategory";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                list.add(new ErrorCategory(id, name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ErrorCategory getErrorCategorieById(int id) {
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

    public void modifier(int id,ErrorCategory ec) {
        try {
            String req = "UPDATE errorcategory SET name=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, ec.getName());
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Error Category updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM errorcategory WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Error Category deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
