package com.example.cocktail.utility;

import android.util.Log;

import com.example.cocktail.model.CockTailModel;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class CockTailParser {
    private static final String TAG = "CockTailParser";

    public static List<CockTailModel> getMatches(String json) {

        List<CockTailModel> modelList = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("ingredients");
            JSONObject first = matches.getJSONObject(0);
            String ingredient = first.getString("strIngredient");
            String description = first.getString("strDescription");
            CockTailModel model = new CockTailModel();
            model.setIngredientStr(ingredient);
            model.setDescriptionStr(description);
            Log.i("i and d", ingredient + " " + description);
            modelList.add(model);

        } catch (JSONException e) {
            Log.e(TAG, "getMatches: THERE WAS AN ERROR PARSING JSON THIS IS FROM THE CLASS");
        }
        return modelList;
    }
}
