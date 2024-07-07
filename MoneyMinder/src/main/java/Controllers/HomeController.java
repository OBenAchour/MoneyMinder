package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeController {

    @FXML
    private StackPane contentPane;

    @FXML
    private void handleForumButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutersujet.fxml"));
            Parent forumView = fxmlLoader.load();
            contentPane.getChildren().clear();  // Clear the existing content
            contentPane.getChildren().add(forumView);  // Add the new content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
