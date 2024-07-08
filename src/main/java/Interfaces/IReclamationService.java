package Interfaces;

import Models.Reclamation;
import java.util.List;

public interface IReclamationService {
    void ajouter(Reclamation r);
    List<Reclamation> afficher();
    Reclamation getReclamationById(int id);
    void modifier(int id,Reclamation r);
    void supprimer(int id);
}
