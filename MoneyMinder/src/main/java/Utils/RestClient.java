package Utils;

import Models.Action;
import Services.ActionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestClient implements Job {
    private final ActionService actionService = new ActionService();

    public  void runJob() {
        try {
            // Define the job and tie it to our MyJob class
            JobDetail job = JobBuilder.newJob(RestClient.class).withIdentity("myJob", "group1").build();

            // Trigger the job to run now, and then every 5 minutes
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever()).build();

            // Grab the Scheduler instance from the Factory
            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // Start the scheduler
            scheduler.start();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Action> getActionFromBVMT() {
        List<Action> actions = new ArrayList<>();
        try {
            String url = "https://www.bvmt.com.tn/rest_api/rest/market/groups/11,12,52,95,99";

            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            // Send the request and get HttpResponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if response status is 200 (OK)
            if (response.statusCode() == 200) {
                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray markets = jsonResponse.getJSONArray("markets");

                // Loop through the markets array
                for (int i = 0; i < markets.length(); i++) {
                    JSONObject market = markets.getJSONObject(i);
                    Action action = new Action();

                    action.setCours(market.getFloat("close"));
                    action.setQuantite(market.getInt("volume"));
                    action.setQuantite_echange(market.getInt("volume"));
                    action.setOuverture(market.getFloat("open"));
                    action.setOuverture(market.getFloat("close"));
                    action.setPrix_haut(market.getFloat("high"));
                    action.setPrix_bas(market.getFloat("low"));
                    action.setVariation(market.getFloat("change"));

                    JSONObject referentiel = market.getJSONObject("referentiel");
                    action.setNom(referentiel.getString("stockName"));
                    JSONObject limit = market.getJSONObject("limit");
                    action.setPrix_achat(limit.getFloat("ask"));
                    action.setPrix_vente(limit.getFloat("bid"));

                    actions.add(action);







                }
            } else {
                System.out.println("HTTP request failed with status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actions;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<Action> actions = getActionFromBVMT();

        actionService.processActions(actions);
        System.out.println("Action updated");


    }
}
