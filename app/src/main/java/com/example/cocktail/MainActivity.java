package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktail.model.CockTailModel;
import com.example.cocktail.model.byRandom.RandomCockTailModel;
import com.example.cocktail.network.CockTailSearchAsyncTask;
import com.example.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.example.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button nameActivity;
    private Button ingredientActivity;
    private Button randomActivity;
    private Button nonAlcoholicActivity;
    private ImageView randomDrinkImage;
    private BitmapFactory randomDrinkImageData;
    private String randomDrinkID;

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
                // I have to get the v.getcontext because the context within the on click interface is
                // different from that of this MainActivity class
                Intent intent = new Intent(v.getContext(), NameActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}
