package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cocktail.model.byName.NameCockTailModel;
import com.example.cocktail.model.byRandom.RandomCockTailModel;
import com.example.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.example.cocktail.network.byIngredient.IngredientCockTailSearchAsyncTask;
import com.example.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;
import com.example.cocktail.utility.RecyclerAdapters.RecyclerViewAlphabetNameAdapter;
import com.example.cocktail.utility.RecyclerAdapters.RecyclerViewIngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity {
    private static final String TAG = "IngredientActivity";
    private Button ingredientSearchButton;
    private EditText ingredientSearchBar;
    private ImageView ingredientImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            Log.d(TAG, "The action bar didn't hide");
        }
        setContentView(R.layout.activity_ingredient);

        ingredientSearchButton = findViewById(R.id.ingredientSearchButton);
        ingredientSearchBar = findViewById(R.id.ingredientSearchBar);
        ingredientImageView = findViewById(R.id.ingredientRandomImage);

        RandomCockTailSearchAsyncTask task = new RandomCockTailSearchAsyncTask();
        task.setRandomCockTailListener(new RandomCockTailSearchAsyncTask.RandomCockTailListener() {
            @Override
            public void randomContract(List<RandomCockTailModel> models) {
                RandomCockTailModel model = models.get(0);
                String imageURL = model.getImageURL();
                Log.d(TAG, imageURL);
                ImageCockTailSearchAsyncTask imagetask = new ImageCockTailSearchAsyncTask();
                imagetask.setImageListener(new ImageCockTailSearchAsyncTask.ImageCockTailListener() {
                    @Override
                    public void ImageContract(Bitmap image) {
                        ingredientImageView.setImageBitmap(image);
                        /*todo: make the image an ingredient, not an actual drink*/
                    }
                });
                imagetask.execute(imageURL);
            }
        });
        task.execute();


        Log.d(TAG, "initializeRecycler: Started THE RECYCLER");
        final RecyclerView recyclerView = this.findViewById(R.id.ingredient_drink_recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setVisibility(View.GONE);
        final ArrayList<String> names = new ArrayList<>();

        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientImageView.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ingredientImageView.setVisibility(View.GONE);
                    }
                });
                IngredientCockTailSearchAsyncTask task = new IngredientCockTailSearchAsyncTask();
                task.setIngredientCockTailListener(new IngredientCockTailSearchAsyncTask.IngredientCockTailListener() {
                    @Override
                    public void IngredientContract(List<NameCockTailModel> modelList) {
                        names.clear();
                        for (int i = 0; i < modelList.size(); i++) {
                            names.add(modelList.get(i).getName());
                        }
                        Log.d(TAG, "This is the size of the LIST and names: " + modelList.size() + " " + names.size());
                        RecyclerViewIngredientAdapter adapter = new RecyclerViewIngredientAdapter(ingredientSearchButton.getContext(), names);
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
