package com.myveryown.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import com.myveryown.cocktail.model.CockTailModel;
import com.myveryown.cocktail.network.ByName.NameCockTailSearchAsyncTask;
import com.myveryown.cocktail.utility.RecyclerAdapters.MyNameRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NameActivity extends AppCompatActivity {

    private static final String TAG = "NameActivity";
    private List<CockTailModel>  drinkNames = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        EditText name_editor = findViewById(R.id.name_editor);


        recyclerView = findViewById(R.id.alcohol_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyNameRecyclerAdapter(drinkNames);
        recyclerView.setAdapter(mAdapter);

        name_editor.setHint(R.string.autofill);
        name_editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getDrinkNames(charSequence);
                Log.i(TAG, "Size of drinkNames after the function: " + drinkNames.size());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getDrinkNames( CharSequence sequence) {
        Log.i(TAG, "This is the sequence: " + sequence.toString());
        Log.i(TAG, "This is the size of the sequence: " + (sequence.length()));
        if (sequence.length() > 1) {
            ArrayList<CockTailModel> temp = new ArrayList<>();
            for (int i = 0; i < drinkNames.size(); i++) {
                CockTailModel model = drinkNames.get(i);
                if (model.getName().contains(sequence)) {
                    temp.add(model);
                }
            }
            mAdapter = new MyNameRecyclerAdapter(temp);
            recyclerView.swapAdapter(mAdapter, true);
            Log.i(TAG, "This is the size of the drinkName inside if: " + (drinkNames.size()));
        }
        else {
            drinkNames.clear();
            NameCockTailSearchAsyncTask task = new NameCockTailSearchAsyncTask();
            task.setNameCockTailListener(new NameCockTailSearchAsyncTask.NameCockTailListener() {
                @Override
                public void nameContract(List<CockTailModel> models) {
                    Log.i(TAG, "Size of models: " + models.size());
                    for (int i = 0; i < models.size(); i++) {
                        drinkNames.add(models.get(i));
                    }
                    mAdapter = new MyNameRecyclerAdapter(drinkNames);
                    recyclerView.setAdapter(mAdapter);
                    Log.i(TAG, "Size of drinkNames" + drinkNames.size());
                }
            });
            task.execute(sequence.toString());
            Log.i(TAG, "This is the length of drinkNames: " + (drinkNames.size()));
        }
    }
}
