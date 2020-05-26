package com.rememberthekey.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.network.ByNonAlcoholic.NonAcoholicCockTailSearchAsyncTask;
import com.rememberthekey.cocktail.utility.RecyclerAdapters.RecyclerViewNon_AlcoholicAdapter;

import java.util.List;

public class NonAlcoholicActivity extends AppCompatActivity {

    private static final String TAG = "NonAlcoholicActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_alcoholic);

        recyclerView = findViewById(R.id.non_alcoholic_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NonAcoholicCockTailSearchAsyncTask task = new NonAcoholicCockTailSearchAsyncTask();
        task.setNon_AlcoholicListener(new NonAcoholicCockTailSearchAsyncTask.Non_AlcoholicListener() {
            @Override
            public void non_alcoholicContract(List<CockTailModel> models) {
                mAdapter = new RecyclerViewNon_AlcoholicAdapter(mContext, models);
                recyclerView.setAdapter(mAdapter);
            }
        });
        task.execute();
    }
}
