package com.myveryown.cocktail.utility;

import android.util.Log;

import com.myveryown.cocktail.model.CockTailModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RandomCockTailParser {
    private static final String TAG = "RandomCockTailParser";

    public static List<CockTailModel> getRandomMatches(String json) {
        List<CockTailModel> modelList = new ArrayList<>();
        List<String> ingredients  = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("drinks");
            JSONObject first = matches.getJSONObject(0);
            JSONArray items = first.names();

            // This grabs all the ingredients from the json object
            for (int i = 0; i < items.length(); i++) {
                String key = items.getString(i);
                String value = first.getString(key);
                if (key.length() > 12 && key.substring(0,12) == "strIngredient" && value != null) {
                    ingredients.add(value);
                }
            }

            // Creating the model for the random object
            CockTailModel model = new CockTailModel(first.getString("strDrink"), ingredients,
                    first.getString("idDrink"), first.getString("strInstructions"), first.getString("strDrinkThumb"),
                    first.getString("strAlcoholic"));
            modelList.add(model);

        } catch (JSONException e) {
            Log.e(TAG, "getMatches: THERE WAS AN ERROR PARSING JSON THIS IS FROM THE CLASS");
        }
        return modelList;
    }
}
