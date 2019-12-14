package com.example.cocktail.network;

import android.os.AsyncTask;

import com.example.cocktail.model.CockTailModel;
import com.example.cocktail.utility.CockTailParser;

import java.util.List;

public class CockTailSearchAsyncTask extends AsyncTask<String, Void, List<CockTailModel>> {

    private CockTailListener listener;

    public interface CockTailListener {
        void contract(List <CockTailModel> model);
    }

    @Override
    protected  List<CockTailModel> doInBackground(String... params) {
        String searchTerm = params[0];

        String responseJson = CockTailSearchHelper.searchCockTails(searchTerm);

        if (responseJson != null) {
            List<CockTailModel> models = CockTailParser.getMatches(responseJson);
            return models;
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<CockTailModel> CockTailModels) {
        super.onPostExecute(CockTailModels);
        listener.contract(CockTailModels);
    }

    public void setCockTailListener(CockTailListener listener) {
        this.listener = listener;
    }
}
