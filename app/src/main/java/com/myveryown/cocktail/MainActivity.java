package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.myveryown.cocktail.model.byRandom.RandomCockTailModel;
import com.myveryown.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.myveryown.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button nameActivity;
    private Button ingredientActivity;
    private Button randomActivity;
    private Button nonAlcoholicActivity;
    private ImageView randomDrinkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            Log.d(TAG, "The action bar didn't hide");
        }
        setContentView(R.layout.activity_main);

        nameActivity = findViewById(R.id.name_search);
        ingredientActivity = findViewById(R.id.ingredient_search);
        randomActivity = findViewById(R.id.random_search);
        nonAlcoholicActivity = findViewById(R.id.non_alcoholic_search);
        randomDrinkImage = findViewById(R.id.randomMainDrinkImage);


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
                        randomDrinkImage.setImageBitmap(image);
                    }
                });
                imagetask.execute(imageURL);
            }
        });
        task.execute();

        nameActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NameActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        ingredientActivity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), IngredientActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        nonAlcoholicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NonAlcoholicActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        randomActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RandomActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
