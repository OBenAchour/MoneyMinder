package Controllers;

import Models.Commentaire;
import Models.Sujet;
import Servicesujet.CmtManager;
import Utils.Myconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentaireListController {

    @FXML
    private TableView<Commentaire> commentaireTable;

    @FXML
    private TableColumn<Commentaire, String> commentaireColumn;

    private Sujet sujet;

    private CmtManager cmtManager = new CmtManager(Myconnection.getInstance().getCnx());

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
        loadCommentaires(); // Recharger les commentaires à chaque fois qu'un sujet est défini
    }

    private void loadCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        Connection connection = Myconnection.getInstance().getCnx();
        try {
            String query = "SELECT id_cmt, cmt FROM commentaire WHERE id_sujet = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, sujet.getId_suj());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commentaire commentaire = new Commentaire(
                        rs.getInt("id_cmt"),
                        rs.getString("cmt"),
                        sujet
                );
                commentaires.add(commentaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Commentaire> observableList = FXCollections.observableArrayList(commentaires);
        commentaireTable.setItems(observableList);
    }

    @FXML
    public void initialize() {
        commentaireColumn.setCellValueFactory(new PropertyValueFactory<>("cmt"));
    }

    @FXML
    private void handleEditAction() {
        Commentaire selectedCommentaire = commentaireTable.getSelectionModel().getSelectedItem();
        if (selectedCommentaire != null) {
            TextInputDialog dialog = new TextInputDialog(selectedCommentaire.getCmt());
            dialog.setTitle("Mise à jour du commentaire");
            dialog.setHeaderText("Mettre à jour le commentaire");
            dialog.setContentText("Nouveau commentaire:");

            Optional<String> resultCommentaire = dialog.showAndWait();
            resultCommentaire.ifPresent(newCommentaire -> {
                selectedCommentaire.setCmt(newCommentaire);
                cmtManager.update(selectedCommentaire);
                loadCommentaires(); // Rafraîchir la liste après mise à jour
            });
        } else {
            showAlert("Veuillez sélectionner un commentaire à mettre à jour.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void handleDeleteCommentAction() {
        Commentaire selectedCommentaire = commentaireTable.getSelectionModel().getSelectedItem();
        if (selectedCommentaire != null) {
            cmtManager.delete(selectedCommentaire);
            commentaireTable.getItems().remove(selectedCommentaire);
        } else {
            showAlert("Veuillez sélectionner un commentaire à supprimer.");
        }
    }
}
