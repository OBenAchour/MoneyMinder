package Controllers;

import Models.Sujet;
import Servicesujet.CmtManager;
import Servicesujet.SujetManager;
import Utils.Myconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SujetListeController {

    @FXML
    private TableView<Sujet> sujetTable;

    @FXML
    private TableColumn<Sujet, String> sujetColumn;

    @FXML
    private TableColumn<Sujet, String> contenuColumn;

    private Connection connection;
    private SujetManager sujetManager;
    private CmtManager cmtManager;

    public SujetListeController() {
        connection = Myconnection.getInstance().getCnx();
        sujetManager = new SujetManager(connection);
        cmtManager = new CmtManager(connection);
    }

    public void initialize() {
        sujetColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        contenuColumn.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        loadSujetList();
    }

    public void loadSujetList() {
        List<Sujet> sujets = sujetManager.getAll();
        ObservableList<Sujet> observableList = FXCollections.observableArrayList(sujets);
        sujetTable.setItems(observableList);
    }

    @FXML
    private void handleUpdateAction() {
        Sujet selectedSujet = sujetTable.getSelectionModel().getSelectedItem();
        if (selectedSujet != null) {
            TextInputDialog dialog = new TextInputDialog(selectedSujet.getTitre());
            dialog.setTitle("Mise à jour du sujet");
            dialog.setHeaderText("Mettre à jour le titre et le contenu du sujet");
            dialog.setContentText("Nouveau titre:");

            Optional<String> resultTitre = dialog.showAndWait();
            resultTitre.ifPresent(newTitre -> {
                TextInputDialog dialogContenu = new TextInputDialog(selectedSujet.getSujet());
                dialogContenu.setTitle("Mise à jour du sujet");
                dialogContenu.setHeaderText("Mettre à jour le titre et le contenu du sujet");
                dialogContenu.setContentText("Nouveau contenu:");

                Optional<String> resultContenu = dialogContenu.showAndWait();
                resultContenu.ifPresent(newContenu -> {
                    selectedSujet.setTitre(newTitre);
                    selectedSujet.setSujet(newContenu);
                    sujetManager.update(selectedSujet);
                    loadSujetList(); // Rafraîchir la liste après mise à jour
                });
            });
        } else {
            showAlert("Veuillez sélectionner un sujet à mettre à jour.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        Sujet selectedSujet = sujetTable.getSelectionModel().getSelectedItem();
        if (selectedSujet != null) {
            sujetManager.delete(selectedSujet);
            loadSujetList();
        } else {
            showAlert("Veuillez sélectionner un sujet à supprimer.");
        }
    }

    @FXML
    private void handleGetAllAction() {
        loadSujetList();
    }

    @FXML
    private void handleAddCommentAction() {
        Sujet selectedSujet = sujetTable.getSelectionModel().getSelectedItem();
        if (selectedSujet != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterCommentaire.fxml"));
                Parent root = loader.load();

                // Passer le sujet sélectionné au contrôleur de la nouvelle fenêtre
                AjouterCommentaireController controller = loader.getController();
                controller.setSujet(selectedSujet);
                controller.setParentController(this); // Passer le contrôleur parent

                Stage stage = new Stage();
                stage.setTitle("Ajouter un Commentaire");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();

                // Recharger la liste des sujets après la fermeture de la fenêtre de commentaire
                loadSujetList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Veuillez sélectionner un sujet pour ajouter un commentaire.");
        }
    }

    @FXML
    private void handleShowCommentsAction() {
        Sujet selectedSujet = sujetTable.getSelectionModel().getSelectedItem();
        if (selectedSujet != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/commentaireList.fxml"));
                Parent root = loader.load();

                // Passer le sujet sélectionné au contrôleur de la nouvelle fenêtre
                CommentaireListController controller = loader.getController();
                controller.setSujet(selectedSujet);

                Stage stage = new Stage();
                stage.setTitle("Liste des Commentaires");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Veuillez sélectionner un sujet pour afficher les commentaires.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}