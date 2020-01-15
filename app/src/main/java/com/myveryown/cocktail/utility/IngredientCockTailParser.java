package com.myveryown.cocktail.utility;

import android.util.Log;

import com.myveryown.cocktail.model.byName.NameCockTailModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IngredientCockTailParser  {
    private static final String TAG = "IngredientCockTailParse";

    public static List<NameCockTailModel> getIngredientMatches(String json) {
        List<NameCockTailModel> modelList = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("drinks");
            JSONObject array;
            for (int i = 0; i < matches.length(); i++) {
                array = matches.getJSONObject(i);
                NameCockTailModel model = new NameCockTailModel(array.getString("strDrink"),array.getString("idDrink"));
                modelList.add(model);
            }
        } catch (JSONException e) {
            Log.e(TAG, "getIngredientMatches: THERE WAS ERROR WITH NAME PARSING, HERE IS THE JSON: " + json);
        }
        return modelList;
    }
}
