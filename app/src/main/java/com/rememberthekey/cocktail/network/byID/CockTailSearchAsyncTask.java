package com.rememberthekey.cocktail.network.byID;

import android.os.AsyncTask;

import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.utility.CockTailParser;

public class CockTailSearchAsyncTask extends AsyncTask<String, Void, CockTailModel> {

    private CockTailListener listener;

    @Override
    protected CockTailModel doInBackground(String... strings) {
        String response = CockTailSearchHelper.searchCockTailWithID(strings[0]);
        if (response != null) {
            CockTailModel models = CockTailParser.getCockTailMatch(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(CockTailModel model) {
        super.onPostExecute(model);
        listener.contract(model);


    }

    public interface CockTailListener {
        void contract (CockTailModel model);
    }

    public void setCockTailListener (CockTailListener listener) {
        this.listener = listener;
    }
}
