package Interfaces;

import Models.Response;
import java.util.List;

public interface IResponseService {
    void ajouter(Response response);
    List<Response> afficher();
    Response getResponseById(int id);
    void modifier(int id,Response response);
    void supprimer(int id);
}
