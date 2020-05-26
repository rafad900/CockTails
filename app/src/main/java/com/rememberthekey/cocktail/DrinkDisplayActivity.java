package com.rememberthekey.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rememberthekey.cocktail.model.CockTailModel;
import com.rememberthekey.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;
import com.rememberthekey.cocktail.network.byID.CockTailSearchAsyncTask;

import java.util.List;

public class DrinkDisplayActivity extends AppCompatActivity {
    private static final String TAG = "DrinkDisplayActivity";

    private ImageView cockTailImageHolder;
    private Button intructionButton;
    private Button ingredientButton;
    private TextView drinkName;
    private TextView instructionOrIngredients;
    private List<String> ingredients;
    private String instructions;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_display);

        cockTailImageHolder = findViewById(R.id.drink_picture_holder);
        intructionButton = findViewById(R.id.instructions_button);
        ingredientButton = findViewById(R.id.ingredient_button);
        drinkName = findViewById(R.id.name_of_drink);
        instructionOrIngredients = findViewById(R.id.ingredient_or_instruction_text);

        Intent intent = getIntent();
        String name = intent.getStringExtra("DRINK_NAME");
        String id = intent.getStringExtra("ID_NUMBER");

        drinkName.setText(name);

        CockTailSearchAsyncTask task = new CockTailSearchAsyncTask();
        task.setCockTailListener(new CockTailSearchAsyncTask.CockTailListener() {
            @Override
            public void contract(final CockTailModel model) {
                ingredients = model.getIngrients();
                instructions = model.getInstructions();
                ImageCockTailSearchAsyncTask task = new ImageCockTailSearchAsyncTask();
                task.setImageListener(new ImageCockTailSearchAsyncTask.ImageCockTailListener() {
                    @Override
                    public void ImageContract(Bitmap image) {
                        cockTailImageHolder.setImageBitmap(image);
                    }
                });
                task.execute(model.getImageURL());
            }
        });

        Log.d(TAG, "HERE IS THE IMAGE URL AFTER: " + imageURL);
        task.execute(id);

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = "";
                for (int i = 0; i < ingredients.size(); i++) {
                    temp += (i + 1) + " - " + ingredients.get(i) + "\n";
                }
                instructionOrIngredients.setText(temp);
            }
        });

        intructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructionOrIngredients.setText(instructions);
            }
        });
    }




}
