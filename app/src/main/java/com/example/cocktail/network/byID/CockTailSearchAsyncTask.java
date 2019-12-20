package com.example.cocktail.network.byID;

import android.os.AsyncTask;

import androidx.constraintlayout.solver.widgets.ResolutionNode;

import com.example.cocktail.model.byRandom.RandomCockTailModel;
import com.example.cocktail.utility.CockTailParser;

import java.util.List;

public class CockTailSearchAsyncTask extends AsyncTask<String, Void, RandomCockTailModel> {

    private CockTailListener listener;

    @Override
    protected RandomCockTailModel doInBackground(String... strings) {
        String response = CockTailSearchHelper.searchCockTailWithID(strings[0]);
        if (response != null) {
            RandomCockTailModel models = CockTailParser.getCockTailMatch(response);
            return models;
        }
        return null;
    }

    @Override
    protected void onPostExecute(RandomCockTailModel model) {
        super.onPostExecute(model);
        listener.contract(model);


    }

    public interface CockTailListener {
        void contract (RandomCockTailModel model);
    }

    public void setCockTailListener (CockTailListener listener) {
        this.listener = listener;
    }
}
