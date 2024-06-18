//package Controller;
//
//import Utils.Myconnection;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class AjouterType {
//
//    @FXML
//    private Button btnV;
//    @FXML
//    private TextField typeTextField;

//    Myconnection cnx2 =null;
//    PreparedStatement pst =null;
//
//    private void showAlert(Alert.AlertType type) {
//        Alert alert = new Alert(type);
//
//        alert.showAndWait();
//    }
//
//    @FXML
//    void createtype(ActionEvent event) {
//
//            String catobj = typeTextField.getText().trim();
//
//
//            // Vérifier que les champs ne sont pas vides
//            if (!catobj.isEmpty() ) {
//                String query = "SELECT * FROM categorieobj WHERE catobj = ?";
//                cnx2 = Myconnection.getInstance();
//                try {
//                    pst = cnx2.getCnx().prepareStatement(query);
//                    pst.setString(1, catobj);
//                    ResultSet rs = pst.executeQuery();
//                    if (rs.next()) {
//                        showAlert(Alert.AlertType.INFORMATION, "Information", "Le produit existe déjà dans la base de données.");
//                    } else {
//                        // Insérer le nouveau produit dans la base de données
//                        String insertQuery = "INSERT INTO categorieobj(catobj) VALUES (?)";
//                        PreparedStatement insertPst = cnx2.getCnx().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
//                        insertPst.setString(1, catobj);
//
//
//                        // Exécuter la requête d'insertion
//                        insertPst.executeUpdate();
//
//
//
//
//
//                        showAlert(Alert.AlertType.INFORMATION);
//
//                        afficher();
//                    }
//
//             {
//                // Afficher une alerte si un champ est vide
//                showAlert(Alert.AlertType.WARNING);
//            }
//
//
//
//
//        } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        private void afficher   () {
//            productSearchModelObservableList.clear();
//            Myconnection connectNow = new Myconnection();
//            Connection connectDB = connectNow.getCnx();
//
//            try {
//                String productViewQuery = "SELECT * from  categorieobj ";
//                Statement statement = connectDB.createStatement();
//                ResultSet queryOutput = statement.executeQuery(productViewQuery);
//                while (queryOutput.next()) {
//
//
//                    String querytype = queryOutput.getString("Type");
//
//                    productSearchModelObservableList.add(new ProductSearchModel( querytype));
//                }
//
//
//
//                productTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
//
//                ProductTableView.setItems(productSearchModelObservableList);
//            } catch (SQLException e) {
//                Logger.getLogger(ProdcutSearchController.class.getName()).log(Level.SEVERE, null, e);
//                e.printStackTrace();
//            }
//
//
//        }}}
//
