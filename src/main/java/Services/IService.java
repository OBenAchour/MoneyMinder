package Services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    void ajouter ( T t) throws SQLException;

    void supprimer (int Id) throws SQLException;

    void modifier (T t ) throws SQLException;

    List<T> recuperer() throws SQLException;

}
