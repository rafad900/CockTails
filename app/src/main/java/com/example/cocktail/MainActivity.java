package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cocktail.model.CockTailModel;
import com.example.cocktail.network.CockTailSearchAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchAlcohol);
        searchButton = findViewById(R.id.alcohol_search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CockTailSearchAsyncTask task = new CockTailSearchAsyncTask();
                task.setCockTailListener(new CockTailSearchAsyncTask.CockTailListener() {
                    @Override
                    public void contract(List<CockTailModel> model) {
                        if (model == null) {
                            Log.i("It", "Is null");
                        }
                        CockTailModel first = model.get(0);

                    }
                });

                String searchTerm = searchEditText.getText().toString();
                task.execute(searchTerm);
            }
        });

    }
}
