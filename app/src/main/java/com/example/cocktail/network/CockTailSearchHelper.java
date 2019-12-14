package com.example.cocktail.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class CockTailSearchHelper {
    private static final String TAG = "CockTailSearchHelper";
    private static final String baseAPIURL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String apiKey = "1/";

    public static String searchCockTails(String input) {

        try {

            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?i=tequila");
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
            return resp;
        } catch (IOException e) {
            Log.e(TAG, "searchCockTails: ", e);
        }
        return null;
    }
}
