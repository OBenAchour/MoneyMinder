package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jfree.data.general.DefaultPieDataset;

public class dashboardoutcome {

    private User u=new User(1,"Ben Achour","Oussema","Oussem@123456",new Date(1996,4,18),"oussema.benachour@esprit.tn");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane bar_chart_dep;

    @FXML
    private AnchorPane piechart_monthperyear;

    @FXML
    private Button to_depense;

    @FXML
    private AnchorPane total_expense_monthly;

    @FXML
    private AnchorPane title;

    @FXML
    private AnchorPane total_expense_yearly;

    @FXML
    void initialize() {
        to_depense.setOnAction(event -> to_depense());
        Models.TransactionStatistics ts = new Models.TransactionStatistics();
        LocalDate currentdate = LocalDate.now();
        int year = currentdate.getYear();
        int month=currentdate.getMonthValue();

        // bar chart des dépenses par catégorie

        Map<String, Double> resdep = ts.getTotalexpenseByCategory(1, u.getId(), month, year);
        ObservableList<XYChart.Data<String, Number>> barChartDataDep = FXCollections.observableArrayList();
        double totalBarValueDep = 0;

        for (Map.Entry<String, Double> entry : resdep.entrySet()) {
            totalBarValueDep += entry.getValue();
        }

        final double finalTotalBarValueDep = totalBarValueDep;
        for (Map.Entry<String, Double> entry : resdep.entrySet()) {
            double percentage = (entry.getValue() / finalTotalBarValueDep) * 100;
            String formattedName = String.format("%s  %.2f%%", entry.getKey(), percentage);
            barChartDataDep.add(new XYChart.Data<>(formattedName, entry.getValue()));
        }

        CategoryAxis xAxisDep = new CategoryAxis();
        NumberAxis yAxisDep = new NumberAxis();
        xAxisDep.setLabel("Catégorie / pourcentage");
        yAxisDep.setLabel("Valeur");

        BarChart<String, Number> barChartDep = new BarChart<>(xAxisDep, yAxisDep);
        barChartDep.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        barChartDep.setTitle("Répartition des dépenses par catégorie");
        XYChart.Series<String, Number> seriesDep = new XYChart.Series<>();
        seriesDep.setName("Dépenses");
        seriesDep.setData(barChartDataDep);
        barChartDep.getData().add(seriesDep);
        barChartDep.setPrefSize(650, 300);
        bar_chart_dep.getChildren().add(barChartDep);


        VBox panelContent = new VBox(2);
        panelContent.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Total dépenses annuel");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.DARKOLIVEGREEN);
        Label valueLabel = new Label(ts.getTotalincomeoutcomeyearly(1,u.getId(),year).toString()+"  DT");
        valueLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        valueLabel.setTextFill(Color.BLACK);
        panelContent.getChildren().addAll(titleLabel, valueLabel);
        total_expense_yearly.getChildren().add(panelContent);

        VBox pane = new VBox(2);
        pane.setAlignment(Pos.CENTER);
        Label title = new Label("Total dépenses mensuel");
        title.setTextFill(Color.DARKOLIVEGREEN);
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        System.out.println(ts.getTotalincomeoutcomemonthly(1,u.getId(),year,month));
        Label value = new Label(ts.getTotalincomeoutcomemonthly(1,u.getId(),year,month).toString()+"  DT");
        value.setTextFill(Color.BLACK);
        value.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        pane.getChildren().addAll(title, value);

        total_expense_monthly.getChildren().add(pane);

        //doughnut ghraf
        Float outcomeyear =ts.getTotalincomeoutcomeyearly(1, u.getId(), year);
        Float outcomemonth =ts.getTotalincomeoutcomemonthly(1, u.getId(), year, month);
        PieChart.Data monthlyIncomeData = new PieChart.Data("Total Monthly Income", outcomeyear-outcomemonth);
        PieChart.Data yearlyIncomeData = new PieChart.Data("Total yearly Income",outcomemonth);
        DefaultPieDataset dataset = new DefaultPieDataset();
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(monthlyIncomeData, yearlyIncomeData);
        pieChart.setTitle("Outcome Distribution for " + ts.getMonthName(month) + " " + year);
        piechart_monthperyear.getChildren().clear(); // Clear previous content
        piechart_monthperyear.getChildren().add(pieChart);
        pieChart.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        piechart_monthperyear.setTopAnchor(pieChart, 0.0);
        piechart_monthperyear.setBottomAnchor(pieChart, 0.0);
        piechart_monthperyear.setLeftAnchor(pieChart, 0.0);
        piechart_monthperyear.setRightAnchor(pieChart, 0.0);
    }

    private void to_depense() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/DepenseH.fxml"));
        try {
            Parent root=loader.load();
            Stage stage=(Stage)to_depense.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
