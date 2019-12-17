package com.example.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cocktail.utility.RecyclerViewAlphabetNameAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NameActivity extends AppCompatActivity {

    private static final String TAG = "NameActivity";
    private final ArrayList<String> alphabet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            Log.d(TAG, "The action bar didn't hide");
        }
        setContentView(R.layout.activity_name);
        Log.d(TAG, "The activity started");
        addLettersToArrayList();
    }

    private void addLettersToArrayList() {
        Log.d(TAG, "Got to add letters to array LIST");
        for (int c = 97; c <= 122; c++) {
            char C = (char)c;
            alphabet.add(String.valueOf(C));
        }
        initializeRecycler();
    }

    private void initializeRecycler() {
        Log.d(TAG, "initializeRecycler: Started THE RECYCLER");
        RecyclerView recyclerView = findViewById(R.id.name_activity_recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAlphabetNameAdapter adapter = new RecyclerViewAlphabetNameAdapter(this, alphabet);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layout);
    }
}
