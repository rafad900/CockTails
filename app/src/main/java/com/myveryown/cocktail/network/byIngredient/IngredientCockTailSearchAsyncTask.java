package com.myveryown.cocktail.network.byIngredient;

import android.os.AsyncTask;

import com.myveryown.cocktail.model.CockTailModel;
import com.myveryown.cocktail.utility.IngredientCockTailParser;

import java.util.List;

public class IngredientCockTailSearchAsyncTask extends AsyncTask<String, Void, List<CockTailModel>> {


    private IngredientCockTailListener listener;

    @Override
    protected List<CockTailModel> doInBackground(String... strings) {
        String response = IngredientCockTailSearchHelper.searchIngredientCockTails(strings[0]);
        if (response != null) {
            List<CockTailModel> models = IngredientCockTailParser.getIngredientMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CockTailModel> models) {
        super.onPostExecute(models);
        listener.IngredientContract(models);
    }

    public interface IngredientCockTailListener {
        void IngredientContract(List<CockTailModel> modelList);
    }

    public void setIngredientCockTailListener(IngredientCockTailListener listener) {
        this.listener = listener;
    }
}
