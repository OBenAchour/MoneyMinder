package Services;

import Models.Status;
import Utils.Myconnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Statusservices implements IService<Status> {

    private Connection cx;

    public Statusservices() {  cx = Myconnection.getInstance().getCnx(); }



    @Override
    public void ajouter(Status status) throws SQLException {
        String req;
        req = "INSERT INTO status VALUES (statusID=?,description=?,report_id=?)";
        PreparedStatement st = cx.prepareStatement(req);
        st.setInt(1, status.getId_status());
        st.setString(2, status.getDescription());
        st.setInt(3, status.getId_rep());
        st.executeUpdate();
    }

    @Override
    public void supprimer(int id_status) throws SQLException {
        String req = "DELETE FROM status WHERE id = ?" ;
        Statement st = cx.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(Status status) throws SQLException {
        String req = "UPDATE status SET status = ? WHERE id = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(1, status.getDescription());
        ps.executeUpdate();






    }

    @Override
    public List<Status> recuperer() throws SQLException {
        List<Status> status = new ArrayList<>();
        String req = "SELECT * FROM status";
        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Status s = new Status();
            s.setDescription(rs.getString("description"));
            s.setId_status(rs.getInt("id"));
            status.add(s);
        }
        return status;



    }


}
