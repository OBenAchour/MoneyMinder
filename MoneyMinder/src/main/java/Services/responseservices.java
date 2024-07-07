package Services;

import Models.Response;
import Utils.Myconnection;
import Utils.Myconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class responseservices implements IService <Response>{

    private Connection cx ;
    public responseservices() { cx = Myconnection.getInstance().getCnx();}

    @Override
    public void ajouter(Response response) throws SQLException {
        String req;
        req = "INSERT INTO response (titre,error_id) VALUES ('" + response.getId_response() + "','" + response.getResponseText() + "')";
        Statement st = cx.createStatement();
        st.executeUpdate(req);
    }



}


