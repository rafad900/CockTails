package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.myveryown.cocktail.model.byRandom.RandomCockTailModel;
import com.myveryown.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.myveryown.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;

import java.util.ArrayList;
import java.util.List;


public class RandomActivity extends AppCompatActivity {

    private static final String TAG = "RandomActivity";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";
    private ImageView randomImageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            Log.d(TAG, "The action bar didn't hide");
        }
        setContentView(R.layout.activity_random);

        randomImageHolder = findViewById(R.id.random_image_holder);
        final ArrayList<RandomCockTailModel> model_holder = new ArrayList<RandomCockTailModel>();

        RandomCockTailSearchAsyncTask task = new RandomCockTailSearchAsyncTask();
        task.setRandomCockTailListener(new RandomCockTailSearchAsyncTask.RandomCockTailListener() {
            @Override
            public void randomContract(List<RandomCockTailModel> models) {
                model_holder.add(models.get(0));
                RandomCockTailModel model = models.get(0);
                String imageURL = model.getImageURL();
                Log.d(TAG, imageURL);
                ImageCockTailSearchAsyncTask imagetask = new ImageCockTailSearchAsyncTask();
                imagetask.setImageListener(new ImageCockTailSearchAsyncTask.ImageCockTailListener() {
                    @Override
                    public void ImageContract(Bitmap image) {
                        randomImageHolder.setImageBitmap(image);
                    }
                });
                imagetask.execute(imageURL);
            }
        });
        task.execute();

        randomImageHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(randomImageHolder.getContext(), DrinkDisplayActivity.class);
                intent.putExtra(IDNUMBER, model_holder.get(0).getId());
                intent.putExtra(NAME, model_holder.get(0).getName());
                randomImageHolder.getContext().startActivity(intent);
            }
        });
    }
}
