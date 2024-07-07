package Controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class InappropriateWordChecker {

    private static final String API_KEY = "355dd00921a9917db0f9f31eca921cd7"; // Votre clé API WebPurify
    private static final String ENDPOINT = "https://api1.webpurify.com/services/rest/";

    public static boolean containsInappropriateWords(String text) {
        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
            String url = ENDPOINT + "?method=webpurify.live.check&api_key=" + API_KEY + "&text=" + encodedText + "&format=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Analyse de la réponse JSON pour déterminer si le texte contient des mots inappropriés
            return response.body().contains("\"found\":\"1\"");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
