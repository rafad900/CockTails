package com.example.cocktail.utility;

import android.nfc.Tag;
import android.util.Log;

import com.example.cocktail.model.byName.NameCockTailModel;
import com.example.cocktail.model.byRandom.RandomCockTailModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NameCockTailParser {
    private static final String TAG = "NameCockTailParser";

    public static List<NameCockTailModel> getNameMatches(String json) {

        List<NameCockTailModel> modelList = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(json);
            JSONArray matches = response.getJSONArray("drinks");
            JSONObject array;
            JSONArray items;
            for (int i = 0; i < matches.length(); i++) {
                array = matches.getJSONObject(i);
                NameCockTailModel model = new NameCockTailModel(array.getString("strDrink"),array.getString("idDrink"));
                modelList.add(model);
            }
        } catch (JSONException e) {
            Log.e(TAG, "getNameMatches: THERE WAS ERROR WITH NAME PARSING");
        }
        return modelList;
    }
}
