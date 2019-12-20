package com.example.cocktail.network.ByNonAlcoholic;

import android.os.AsyncTask;

import com.example.cocktail.model.byName.NameCockTailModel;
import com.example.cocktail.utility.NameCockTailParser;

import org.w3c.dom.NamedNodeMap;

import java.util.List;

public class NonAcoholicCockTailSearchAsyncTask extends AsyncTask<String, Void, List<NameCockTailModel>> {

    private Non_AlcoholicListener listener;

    @Override
    protected List<NameCockTailModel> doInBackground(String... strings) {
        String response = NonAlcoholicCockTailSearchHelper.searchNon_Alcoholic();
        if (response != null) {
            List<NameCockTailModel> models = NameCockTailParser.getNameMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<NameCockTailModel> models) {
        super.onPostExecute(models);
        listener.non_alcoholicContract(models);
    }
    public interface Non_AlcoholicListener {
        void non_alcoholicContract(List<NameCockTailModel> models);
    }

    public void setNon_AlcoholicListener (Non_AlcoholicListener listener) {
        this.listener = listener;
    }
}
