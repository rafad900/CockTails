package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cocktail.model.byRandom.RandomCockTailModel;
import com.example.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.example.cocktail.network.byRandom.RandomCockTailSearchAsyncTask;

import java.util.List;

public class RandomActivity extends AppCompatActivity {

    private static final String TAG = "RandomActivity";
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
                Toast.makeText(v.getContext(), "The image was pressed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
