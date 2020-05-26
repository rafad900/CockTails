package com.rememberthekey.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.network.byIngredient.IngredientCockTailSearchAsyncTask;
import com.rememberthekey.cocktail.utility.RecyclerAdapters.RecyclerViewIngredientAdapter;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {
    private static final String TAG = "IngredientActivity";
    private Button ingredientSearchButton;
    private EditText ingredientSearchBar;
    private TextView messageView;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredient);

        ingredientSearchButton = findViewById(R.id.ingredientSearchButton);
        ingredientSearchBar = findViewById(R.id.ingredientSearchBar);
        messageView = findViewById(R.id.message_view);

        recyclerView = findViewById(R.id.ingredient_drink_recycler);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ingredientName = ingredientSearchBar.getText().toString();

                IngredientCockTailSearchAsyncTask task = new IngredientCockTailSearchAsyncTask();
                task.setIngredientCockTailListener(new IngredientCockTailSearchAsyncTask.IngredientCockTailListener() {
                    @Override
                    public void IngredientContract(List<CockTailModel> modelList) {
                        Log.i(TAG, "Size of the modellist: " + modelList.size());
                        mAdapter = new RecyclerViewIngredientAdapter(mContext, modelList);
                        recyclerView.swapAdapter(mAdapter, true);
                        String resultMessage  = "Here are the drinks with ingredient: " + ingredientName;
                        messageView.setText(resultMessage);
                    }
                });
                if (ingredientName.length() < 1) {
                    String warning = "Ingredient name not given";
                    messageView.setText(warning);
                    messageView.setVisibility(View.VISIBLE);
                } else {
                    String searching = "Searching for drinks with ingredient: " + ingredientName;
                    messageView.setText(searching);
                    messageView.setVisibility(View.VISIBLE);
                    task.execute(ingredientName);
                }
            }
        });
    }
}
