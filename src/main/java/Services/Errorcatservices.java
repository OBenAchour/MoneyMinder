package Services;

import Models.Errorcat;
import Utils.Myconnection;
import Utils.Myconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Errorcatservices implements IService<Errorcat> {

    private Connection cx ;
    public Errorcatservices() { cx = Myconnection.getInstance().getCnx();}


    @Override
    public void ajouter(Errorcat errorcat) throws SQLException {
        String req ;
        req = "INSERT INTO errorcategory (titre,error_id) VALUES ('"+errorcat.getTitre()+"', "+errorcat.getError_id()+")" ;
        Statement st = cx.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void supprimer(int error_id) throws SQLException {
        String req = "DELETE FROM errorcategory WHERE Id ="+error_id;
        Statement st = cx.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(Errorcat errorcat) throws SQLException {
        String req ="UPDATE errorcategory SET (titre) = ? WHERE Id = ? ";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(1, errorcat.getTitre());
        ps.executeUpdate();


    }

    @Override
    public List<Errorcat> recuperer() throws SQLException {
        List<Errorcat> errorcats = new ArrayList<>();
        String req = "SELECT * FROM errorcategory";
        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Errorcat errorcat = new Errorcat();
            errorcat.setTitre(rs.getString("titre"));
            errorcat.setError_id(rs.getInt("error_id"));
            errorcats.add(errorcat);


        }
        return errorcats;
    }

}
