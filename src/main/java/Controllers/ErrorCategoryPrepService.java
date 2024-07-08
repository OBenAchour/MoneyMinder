package Controllers;

import Models.ErrorCategory;
import Services.ErrorCategoryService;

import java.util.List;
import java.util.Optional;

public class ErrorCategoryPrepService {
    ErrorCategoryService errorCategoryService = new ErrorCategoryService();

    public List<ErrorCategory> getAllErrorCategories() {
        List list = errorCategoryService.afficher();
        return list;
    }

    public ErrorCategory findByName(String name) {

        Optional<ErrorCategory> errorCategorylist = errorCategoryService.afficher().stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();

        return errorCategorylist.orElse(null);
    }
}
