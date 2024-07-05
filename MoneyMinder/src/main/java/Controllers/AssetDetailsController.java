package Controllers;//package Controllers;

import Models.Panier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

public class AssetDetailsController {

    @FXML
    private Label labelId;

    @FXML
    private Label labelTitre;

    @FXML
    private Label labelPrix;

    private Panier asset;

    public void setAssetDetails(Panier asset) {
        this.asset = asset;
        labelId.setText("ID: " + asset.getIdAssets());
        labelTitre.setText("Titre: " + asset.getTitre());
        labelPrix.setText("Prix: " + asset.getPrix());
    }


    @FXML
    private void telechargerPdf() {
        String fileName = "AssetDetails_" + UUID.randomUUID().toString() + ".pdf"; // Génère un nom de fichier unique

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("Détails de l'Asset"));
            document.add(new Paragraph("ID: " + asset.getIdAssets()));
            document.add(new Paragraph("Titre: " + asset.getTitre()));
            document.add(new Paragraph("Prix: " + asset.getPrix()));
            document.close();

            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("PDF généré avec succès : " + fileName);
            alert.showAndWait();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();

            // Afficher une alerte d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la génération du PDF.");
            alert.showAndWait();
        }
    }

    @FXML
    private void annuler() {
        // Close the details window or perform any other cancellation actions
        labelId.getScene().getWindow().hide();
    }
}
