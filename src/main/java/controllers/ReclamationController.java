
package controllers;

import entities.ErrorCategory;
import entities.Reclamation;
import entities.Status;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Callback;
import services.ErrorCategoryService;
import services.ReclamationService;

public class ReclamationController {

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<String> errorCategoryComboBox;

    @FXML
    private TreeTableColumn<Reclamation, String> descriptionColumn;

    @FXML
    private TreeTableColumn<Reclamation, String> titleColumn;

    @FXML
    private TreeTableColumn<Reclamation, String> errorCategoryColumn;

    @FXML
    private TreeTableColumn<Reclamation, String> statusColumn;

    @FXML
    private TreeTableColumn<Reclamation, Void> actionColumn;

    @FXML
    private TreeTableView<Reclamation> reclamationTreeTableView;

    @FXML
    private Button addButton;

    private ReclamationService reclamationService;
    private ErrorCategoryService errorCategoryService;
    private ObservableList<Reclamation> reclamationList;
    private ErrorCategoryPrepService errorCategoryPrepService = new ErrorCategoryPrepService();

    @FXML
    public void initialize() {
        reclamationService = new ReclamationService();
        errorCategoryService = new ErrorCategoryService();
        reclamationList = FXCollections.observableArrayList(reclamationService.afficher());

        setupComboBoxes();
        setupColumns();
        setupTableView();
        loadData();
    }

    private void setupComboBoxes() {
        ObservableList<String> errorCategories = FXCollections.observableArrayList();
        for (ErrorCategory category : errorCategoryService.afficher()) {
            errorCategories.add(category.getName());
        }
        errorCategoryComboBox.setItems(errorCategories);
    }

    private void setupColumns() {
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getDescription()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getTitle()));
        errorCategoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getErrorCategory().getName()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getStatus().toString()));

        setupCellFactories();
    }

    private void setupCellFactories() {
        descriptionColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        titleColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        errorCategoryColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        statusColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

        actionColumn.setCellFactory(getRemoveButtonCellFactory());
    }

    private Callback<TreeTableColumn<Reclamation, Void>, TreeTableCell<Reclamation, Void>> getRemoveButtonCellFactory() {
        return new Callback<TreeTableColumn<Reclamation, Void>, TreeTableCell<Reclamation, Void>>() {
            @Override
            public TreeTableCell<Reclamation, Void> call(final TreeTableColumn<Reclamation, Void> param) {
                return new TreeTableCell<Reclamation, Void>() {
                    final Button btn = new Button("Remove");

                    {
                        btn.getStyleClass().add("button-remove");
                        btn.setOnAction(event -> {
                            Reclamation reclamation = getTreeTableView().getTreeItem(getIndex()).getValue();
                            reclamationService.supprimer(reclamation.getId());
                            reclamationList.remove(reclamation);
                            getTreeTableView().getRoot().getChildren().remove(getTreeTableView().getTreeItem(getIndex()));
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
            }
        };
    }

    private void setupTableView() {
        reclamationTreeTableView.setEditable(true);
        reclamationTreeTableView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        setupEditableCells();
    }

    private void setupEditableCells() {
        setupEditableCell(descriptionColumn, (reclamation, newValue) -> reclamation.setDescription(newValue));
        setupEditableCell(titleColumn, (reclamation, newValue) -> reclamation.setTitle(newValue));
        setupEditableCell(errorCategoryColumn, (reclamation, newValue) -> {
            ErrorCategory category = errorCategoryPrepService.findByName(newValue);
            if (category != null) {
                reclamation.setErrorCategory(category);
            }
        });
    }

    private void setupEditableCell(TreeTableColumn<Reclamation, String> column, CellEditHandler<Reclamation, String> editHandler) {
        column.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        column.setOnEditCommit(event -> {
            TreeItem<Reclamation> item = event.getTreeTablePosition().getTreeItem();
            if (item != null) {
                Reclamation reclamation = item.getValue();
                editHandler.handle(reclamation, event.getNewValue());
                System.out.println(reclamation.getId());
                reclamationService.modifier(reclamation.getId(), reclamation);
            }
        });
    }

    private void loadData() {
        TreeItem<Reclamation> root = new TreeItem<>(new Reclamation());
        for (Reclamation reclamation : reclamationList) {
            root.getChildren().add(new TreeItem<>(reclamation));
        }
        reclamationTreeTableView.setRoot(root);
        reclamationTreeTableView.setShowRoot(false);
    }

    @FXML
    public void addReclamation() {
        String description = descriptionTextField.getText();
        String title = titleTextField.getText();
        ErrorCategory errorCategory = getSelectedErrorCategory();
        if (description == null || title == null || errorCategory == null) {
            showError("Description, title, and error category must not be null");
            return;
        }
        // here i added user id static until user management module finish
        Reclamation newReclamation = new Reclamation(title, description, errorCategory, 1);
        reclamationService.ajouter(newReclamation);
        reclamationList.add(newReclamation);

        reclamationTreeTableView.getRoot().getChildren().add(new TreeItem<>(newReclamation));
    }

    private ErrorCategory getSelectedErrorCategory() {
        String selectedCategory = errorCategoryComboBox.getSelectionModel().getSelectedItem();
        return errorCategoryPrepService.findByName(selectedCategory);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FunctionalInterface
    private interface CellEditHandler<T, S> {
        void handle(T entity, S newValue);
    }
}
//    private void showError(String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
