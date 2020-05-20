package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.myveryown.cocktail.model.CockTailModel;
import com.myveryown.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.myveryown.cocktail.network.byIngredient.IngredientCockTailSearchAsyncTask;
import com.myveryown.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;
import com.myveryown.cocktail.utility.RecyclerAdapters.RecyclerViewIngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity {
    private static final String TAG = "IngredientActivity";
    private Button ingredientSearchButton;
    private EditText ingredientSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredient);

        ingredientSearchButton = findViewById(R.id.ingredientSearchButton);
        ingredientSearchBar = findViewById(R.id.ingredientSearchBar);

        Log.d(TAG, "initializeRecycler: Started THE RECYCLER");
        final RecyclerView recyclerView = this.findViewById(R.id.ingredient_drink_recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setVisibility(View.GONE);
        final ArrayList<String> names = new ArrayList<>();
        final ArrayList<String> mID = new ArrayList<>();

        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientCockTailSearchAsyncTask task = new IngredientCockTailSearchAsyncTask();
                task.setIngredientCockTailListener(new IngredientCockTailSearchAsyncTask.IngredientCockTailListener() {
                    @Override
                    public void IngredientContract(List<CockTailModel> modelList) {
                        names.clear();
                        mID.clear();
                        for (int i = 0; i < modelList.size(); i++) {
                            names.add(modelList.get(i).getName());
                            mID.add(modelList.get(i).getId());
                        }
                        Log.d(TAG, "This is the size of the LIST and names: " + modelList.size() + " " + names.size());
                        RecyclerViewIngredientAdapter adapter = new RecyclerViewIngredientAdapter(ingredientSearchButton.getContext(), names, mID);
                        recyclerView.setAdapter(adapter);
                        recyclerView.animate().alpha(1.0f).setDuration(100).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
                String str = ingredientSearchBar.getText().toString();
                str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
                task.execute(str);
            }
        });
    }
}
