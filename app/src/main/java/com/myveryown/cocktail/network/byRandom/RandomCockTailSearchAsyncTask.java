package com.myveryown.cocktail.network.byRandom;

import android.os.AsyncTask;

import com.myveryown.cocktail.model.byRandom.RandomCockTailModel;
import com.myveryown.cocktail.utility.RandomCockTailParser;

import java.util.List;

public class RandomCockTailSearchAsyncTask extends AsyncTask<String, Void, List<RandomCockTailModel>> {

    private RandomCockTailListener listener;

    public interface RandomCockTailListener {
        void randomContract (List <RandomCockTailModel> models);
    }
    @Override
    protected List<RandomCockTailModel> doInBackground(String... strings) {
        String response = RandomCockTailSearchHelper.searchRandomCockTail();

        if (response != null) {
            List<RandomCockTailModel> models = RandomCockTailParser.getRandomMatches(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<RandomCockTailModel> models) {
        super.onPostExecute(models);
        listener.randomContract(models);
    }

    public void setRandomCockTailListener (RandomCockTailListener listener) {
        this.listener = listener;
    }
}
