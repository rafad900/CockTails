package com.example.cocktail.network.ByName;

import android.os.AsyncTask;

import com.example.cocktail.model.byName.NameCockTailModel;
import com.example.cocktail.utility.NameCockTailParser;

import java.util.List;

public class NameCockTailSearchAsyncTask extends AsyncTask<String, Void, List<NameCockTailModel>> {
    private NameCockTailListener listener;

    @Override
    protected List<NameCockTailModel> doInBackground(String... strings) {
        String response = NameCockTailSearchHelper.searchNameCockTail(strings[0]);

        if (response != null) {
            List<NameCockTailModel> models = NameCockTailParser.getNameMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<NameCockTailModel> models) {
        super.onPostExecute(models);
        listener.nameContract(models);
    }

    public interface NameCockTailListener {
        void nameContract (List<NameCockTailModel> models);
    }

    public void setNameCockTailListener (NameCockTailListener listener) {
        this.listener = listener;
    }
}
