package com.rememberthekey.cocktail.network.ByName;

import android.os.AsyncTask;

import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.utility.NameCockTailParser;

import java.util.List;

public class NameCockTailSearchAsyncTask extends AsyncTask<String, Void, List<CockTailModel>> {
    private NameCockTailListener listener;

    @Override
    protected List<CockTailModel> doInBackground(String... strings) {
        String response = NameCockTailSearchHelper.searchNameCockTail(strings[0]);

        if (response != null) {
            List<CockTailModel> models = NameCockTailParser.getNameMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CockTailModel> models) {
        super.onPostExecute(models);
        listener.nameContract(models);
    }

    public interface NameCockTailListener {
        void nameContract (List<CockTailModel> models);
    }

    public void setNameCockTailListener (NameCockTailListener listener) {
        this.listener = listener;
    }
}
