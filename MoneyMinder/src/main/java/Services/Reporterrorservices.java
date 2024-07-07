package Services;

import Models.Reporterror;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reporterrorservices implements IService<Reporterror> {
    private Connection cx ;
    public Reporterrorservices() { cx = Myconnection.getInstance().getCnx();}



    @Override
    public void ajouter(Reporterror reporterror ) throws SQLException{
        String req;
        req = "INSERT INTO reporterror (id_rep,errorCode,id_user,comment, Errorcat) VALUES (" +
                "('"+reporterror.getId_rep()+"','"+reporterror.getErrorCode()+"','"+reporterror.getId_user()+"','"+reporterror.getComment()+"','"+reporterror.getErrorcat()+"');";
        Statement st = cx.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void supprimer (int id_rep) throws SQLException {
        String req = "DELETE FROM reporterror WHERE id_rep=?";
        Statement st = cx.createStatement();
        st.executeUpdate(req);



    }

    @Override
    public void modifier(Reporterror reporterror) throws SQLException {
        String req ="UPDATE reporterror SET = ? WHERE id_rep= ? ";
        PreparedStatement pst = cx.prepareStatement(req);
        pst.setInt(1, reporterror.getId_rep());
        pst.executeUpdate();


    }

    @Override
    public List<Reporterror> recuperer() throws SQLException {
        List<Reporterror> reporterrors = new ArrayList<Reporterror>();
        String req = "SELECT * FROM reporterror";
        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reporterror reporterror = new Reporterror();
            reporterror.setId_rep(rs.getInt("id_rep"));
            reporterror.setComment(rs.getString("comment"));
            reporterror.setErrorcat(rs.getString("Errorcat"));
            reporterrors.add(reporterror);
        }
        return reporterrors;
    }


}
