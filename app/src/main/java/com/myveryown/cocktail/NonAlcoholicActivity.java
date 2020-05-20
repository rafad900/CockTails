package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myveryown.cocktail.model.CockTailModel;
import com.myveryown.cocktail.network.ByNonAlcoholic.NonAcoholicCockTailSearchAsyncTask;
import com.myveryown.cocktail.utility.RecyclerAdapters.RecyclerViewNon_AlcoholicAdapter;

import java.util.ArrayList;
import java.util.List;

public class NonAlcoholicActivity extends AppCompatActivity {

    private static final String TAG = "NonAlcoholicActivity";
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> mID = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG, "The action bar didn't hide");
        }
        setContentView(R.layout.activity_non_alcoholic);

        final RecyclerView non_alcoholic_recycler = this.findViewById(R.id.non_alcoholic_recycler);
        final LinearLayoutManager namelayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        non_alcoholic_recycler.setLayoutManager(namelayout);
        final Context context = this;

        NonAcoholicCockTailSearchAsyncTask task = new NonAcoholicCockTailSearchAsyncTask();
        task.setNon_AlcoholicListener(new NonAcoholicCockTailSearchAsyncTask.Non_AlcoholicListener() {
            @Override
            public void non_alcoholicContract(List<CockTailModel> models) {
                names.clear();
                mID.clear();
                for (int i = 0; i < models.size(); i++) {
                    names.add(models.get(i).getName());
                    mID.add(models.get(i).getId());
                }
                RecyclerViewNon_AlcoholicAdapter adapter = new RecyclerViewNon_AlcoholicAdapter(context, names, mID);
                non_alcoholic_recycler.setAdapter(adapter);
            }
        });
        task.execute();

    }
}
