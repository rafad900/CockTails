package com.rememberthekey.cocktail.network.byRandom;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomCockTailSearchHelper {
    private static final String TAG = "RandomCockTailSearchHel";
    private static final String baseURL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String apiKEY = "1/";
    private static final String query = "random.php";

    public static String searchRandomCockTail() {
        try {

            URL url = new URL(baseURL+apiKEY+query);
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
