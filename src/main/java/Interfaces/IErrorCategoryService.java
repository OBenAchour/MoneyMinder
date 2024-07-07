package services;

import entities.ErrorCategory;
import java.util.List;

public interface IErrorCategoryService {
    void ajouter(ErrorCategory ec);
    List<ErrorCategory> afficher();
    ErrorCategory getErrorCategorieById(int id);
    void modifier(int id,ErrorCategory ec);
    void supprimer(int id);
}
