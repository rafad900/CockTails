package com.myveryown.cocktail.utility;

import android.util.Log;

import com.myveryown.cocktail.model.CockTailModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NameCockTailParser {
    private static final String TAG = "NameCockTailParser";

    public static List<CockTailModel> getNameMatches(String json) {

        List<CockTailModel> modelList = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("drinks");
            JSONObject array;

            for (int i = 0; i < matches.length(); i++) {
                array = matches.getJSONObject(i);
                CockTailModel model = new CockTailModel(array.getString("strDrink"),array.getString("idDrink"), array.getString("strDrinkThumb"));
                modelList.add(model);
            }
        } catch (JSONException e) {
            Log.e(TAG, "getNameMatches: THERE WAS ERROR WITH NAME PARSING");
        }
        return modelList;
    }
}
