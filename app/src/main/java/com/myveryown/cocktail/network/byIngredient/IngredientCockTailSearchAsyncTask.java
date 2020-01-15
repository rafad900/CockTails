package com.myveryown.cocktail.network.byIngredient;

import android.os.AsyncTask;

import com.myveryown.cocktail.model.byName.NameCockTailModel;
import com.myveryown.cocktail.utility.IngredientCockTailParser;

import java.util.List;

public class IngredientCockTailSearchAsyncTask extends AsyncTask<String, Void, List<NameCockTailModel>> {


    private IngredientCockTailListener listener;

    @Override
    protected List<NameCockTailModel> doInBackground(String... strings) {
        String response = IngredientCockTailSearchHelper.searchIngredientCockTails(strings[0]);
        if (response != null) {
            List<NameCockTailModel> models = IngredientCockTailParser.getIngredientMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<NameCockTailModel> models) {
        super.onPostExecute(models);
        listener.IngredientContract(models);
    }

    public interface IngredientCockTailListener {
        void IngredientContract(List<NameCockTailModel> modelList);
    }

    public void setIngredientCockTailListener(IngredientCockTailListener listener) {
        this.listener = listener;
    }
}
