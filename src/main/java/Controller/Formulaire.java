package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Formulaire {

    @FXML
    private Button btnret;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnV;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField titreTextField;

    @FXML
    private TextField budgetTextField;

    @FXML
    private TextField moisTextField;

    @FXML
    private TextField commentaireTextField;

    @FXML
    void initialize() {
        btnret.setOnAction(event -> retourAjouterObjectif());
        btnHome.setOnAction(event -> retourHome());
        btnV.setOnAction(event -> validerAjout());
    }

    private void retourAjouterObjectif() {
        navigateTo("/AjouterObjectif.fxml");
    }

    private void retourHome() {
        navigateTo("/Home.fxml");
    }

    private void validerAjout() {
        //String type = typeComboBox.getValue();
        String titre = titreTextField.getText();
        String montant_globale = budgetTextField.getText();
        String mois = moisTextField.getText();
        String commentaire = commentaireTextField.getText();

        // Connexion à la base de données et insertion des données
        String url = "jdbc:mysql://localhost:3306/moneyminderdb";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO objectif (titre, montant_globale, mois, commentaire) VALUES ( ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //pstmt.setString(1, type);
            pstmt.setString(1, titre);
            pstmt.setString(2, montant_globale);
            pstmt.setString(3, mois);
            pstmt.setString(4, commentaire);

            pstmt.executeUpdate();
            System.out.println("Les données ont été ajoutées avec succès.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) btnV.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
