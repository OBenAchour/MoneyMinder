package Controllers;//package Controllers;

import Models.Panier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("AssetDetails.pdf"));
            document.open();
            document.add(new Paragraph("Détails de l'Asset"));
            document.add(new Paragraph("ID: " + asset.getIdAssets()));
            document.add(new Paragraph("Titre: " + asset.getTitre()));
            document.add(new Paragraph("Prix: " + asset.getPrix()));
            document.close();

            // Show a success message or perform further actions
            System.out.println("PDF généré avec succès.");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void annuler() {
        // Close the details window or perform any other cancellation actions
        labelId.getScene().getWindow().hide();
    }
}
