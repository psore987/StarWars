package PasAPas;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONObject;

public class API {

    // URL de base de l'API pour les planètes
    private static final String PLANETS_API_URL = "https://swapi.dev/api/planets/";

    public JSONObject getPlanets(String searchquery) {
        try {
            // Construction de l'URL de l'API avec la recherche si spécifiée
            String urlString = PLANETS_API_URL;
            if (searchquery != null && !searchquery.isEmpty()) {
                urlString += "?search=" + searchquery;
            }

            // Création de l'objet URI pour l'URL de l'API
            URI uri = new URI(urlString);
            // Ouverture d'une connexion HTTP
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Lecture de la réponse de l'API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();

            // Conversion de la réponse en JSONObject
            return new JSONObject(responseBuilder.toString());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
