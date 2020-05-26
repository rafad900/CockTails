package com.rememberthekey.cocktail.utility;

import android.util.Log;

import com.rememberthekey.cocktail.model.CockTailModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CockTailParser {
    private static final String TAG = "CockTailParser";

    public static CockTailModel getCockTailMatch(String json) {
        CockTailModel model = null;
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("drinks");
            JSONObject first = matches.getJSONObject(0);
            JSONArray items = first.names();

            // This grabs all the ingredients from the json object
            for (int i = 0; i < items.length(); i++) {
                String key = items.getString(i);
                String value = first.getString(key);
                if (key.length() >= 13 && key.substring(0,13).equals("strIngredient") && value != "null") {
                    ingredients.add(value);
                }
            }

            // Creating the model for the random object
            model = new CockTailModel(first.getString("strDrink"), ingredients,
                    first.getString("idDrink"), first.getString("strInstructions"), first.getString("strDrinkThumb"),
                    first.getString("strAlcoholic"));

        } catch (JSONException e) {
            Log.e(TAG, "getMatches: THERE WAS AN ERROR PARSING JSON THIS IS FROM THE CLASS");
        }
        return model;
    }
}
