package Controllers;

import Models.ErrorCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import Services.ErrorCategoryService;

public class errorCategoryController {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    @FXML
    private TableView<ErrorCategory> errorCategoryTableView;

    @FXML
    private TableColumn<ErrorCategory, String> nameColumn;

    @FXML
    private TableColumn<ErrorCategory, Void> removeColumn;

    private ErrorCategoryService errorCategoryService;
    private ObservableList<ErrorCategory> errorCategoryList;

    @FXML
    public void initialize() {
        tableview();
    }

    public void tableview() {
        errorCategoryService = new ErrorCategoryService();
        errorCategoryList = FXCollections.observableArrayList(errorCategoryService.afficher());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            ErrorCategory errorCategory = event.getRowValue();
            System.out.println(errorCategory);
            errorCategory.setName(event.getNewValue());
            int id = errorCategory.getId();
            errorCategoryService.modifier(id, errorCategory);
        });

        removeColumn.setCellFactory(new Callback<TableColumn<ErrorCategory, Void>, TableCell<ErrorCategory, Void>>() {
            @Override
            public TableCell<ErrorCategory, Void> call(final TableColumn<ErrorCategory, Void> param) {
                final TableCell<ErrorCategory, Void> cell = new TableCell<ErrorCategory, Void>() {
                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ErrorCategory errorCategory = getTableView().getItems().get(getIndex());
                            errorCategoryService.supprimer(errorCategory.getId());
                            errorCategoryList.remove(errorCategory);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });

        errorCategoryTableView.setItems(errorCategoryList);
        errorCategoryTableView.setEditable(true);
        System.out.println(errorCategoryList);
    }

    @FXML
    public void addErrorCategory(ActionEvent event) {
        if (nameTextField != null) {
            String name = nameTextField.getText();
            System.out.println("Name entered: " + name);
            ErrorCategory errorCategory = new ErrorCategory(name);
            errorCategoryService.ajouter(errorCategory);
            errorCategoryList.add(errorCategory);
        } else {
            System.err.println("NameTextField is null");
        }
    }
}
