package com.rememberthekey.cocktail.network.byRandom;

import android.os.AsyncTask;

import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.utility.RandomCockTailParser;

import java.util.List;

public class RandomCockTailSearchAsyncTask extends AsyncTask<String, Void, List<CockTailModel>> {

    private RandomCockTailListener listener;

    public interface RandomCockTailListener {
        void randomContract (List <CockTailModel> models);
    }
    @Override
    protected List<CockTailModel> doInBackground(String... strings) {
        String response = RandomCockTailSearchHelper.searchRandomCockTail();

        if (response != null) {
            List<CockTailModel> models = RandomCockTailParser.getRandomMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CockTailModel> models) {
        super.onPostExecute(models);
        listener.randomContract(models);
    }

    public void setRandomCockTailListener (RandomCockTailListener listener) {
        this.listener = listener;
    }
}
