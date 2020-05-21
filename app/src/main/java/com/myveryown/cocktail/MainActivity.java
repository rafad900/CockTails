package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.myveryown.cocktail.model.CockTailModel;
import com.myveryown.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.myveryown.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;

import java.net.ContentHandler;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private Button nameActivity;
    private Button ingredientActivity;
    private Button randomActivity;
    private Button nonAlcoholicActivity;
    private ImageView randomDrinkImage;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nameActivity = findViewById(R.id.name_search);
        ingredientActivity = findViewById(R.id.ingredient_search);
        randomActivity = findViewById(R.id.random_search);
        nonAlcoholicActivity = findViewById(R.id.non_alcoholic_search);
        randomDrinkImage = findViewById(R.id.randomMainDrinkImage);


        RandomCockTailSearchAsyncTask task = new RandomCockTailSearchAsyncTask();
        task.setRandomCockTailListener(new RandomCockTailSearchAsyncTask.RandomCockTailListener() {
            @Override
            public void randomContract(List<CockTailModel> models) {
                CockTailModel model = models.get(0);
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
                randomActivity.setText("Looking for random drink");
                RandomCockTailSearchAsyncTask task = new RandomCockTailSearchAsyncTask();
                task.setRandomCockTailListener(new RandomCockTailSearchAsyncTask.RandomCockTailListener() {
                    @Override
                    public void randomContract(List<CockTailModel> models) {
                        Log.d(TAG, "The random contract listener was clicked");
                        Intent intent = new Intent(mContext, DrinkDisplayActivity.class);
                        intent.putExtra(IDNUMBER,  models.get(0).getId());
                        intent.putExtra(NAME, models.get(0).getName());
                        mContext.startActivity(intent);
                    }
                });
                task.execute();
            }
        });
    }
}
