package com.example.cocktail.network.byIngredient;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IngredientCockTailSearchHelper {

    private static final String TAG = "IngredientCockTailSearc";
    private static final String baseURL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String apiKey = "1/";
    private static final String query = "filter.php?i=";

    public static String searchIngredientCockTails(String input) {
        try {
            URL url = new URL(baseURL+apiKey+query+input);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            StringBuilder response = new StringBuilder();

            int i;
            char c;
            while ((i = in.read()) != -1) {
                c = (char) i;
                response.append(c);
            }

            String resp = response.toString();
            urlConnection.disconnect();
            Log.d(TAG, "This was the query and the response: " + baseURL+apiKey+query+input + " " + resp);
            return resp;
        } catch (IOException e) {
            Log.e(TAG, "searchCockTails: ", e);
        }
        return null;
    }

}
