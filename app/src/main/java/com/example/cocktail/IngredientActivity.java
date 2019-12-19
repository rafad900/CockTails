package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;

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

        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientCockTailSearchAsyncTask task = new IngredientCockTailSearchAsyncTask();
                task.setIngredientCockTailListener(new IngredientCockTailSearchAsyncTask.IngredientCockTailListener() {
                    @Override
                    public void IngredientContract(List<NameCockTailModel> modelList) {
                        ingredientImageView.setVisibility(View.GONE);
                        /*todo: set the recycler view to the drinks made with this
                        *  ingredient*/

                    }
                });
                task.execute(ingredientSearchBar.getText().toString());
            }
        });
    }
}
