package com.myveryown.cocktail.utility.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myveryown.cocktail.DrinkDisplayActivity;
import com.myveryown.cocktail.R;

import java.util.ArrayList;

public class RecyclerViewIngredientAdapter extends RecyclerView.Adapter<RecyclerViewIngredientAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewIngredientA";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private ArrayList<String> mNames;
    private ArrayList<String> mIDs;
    private Context mContext;


    public RecyclerViewIngredientAdapter (Context context, ArrayList<String> names, ArrayList<String> id) {
        this.mContext = context;
        this.mIDs = id;
        this.mNames = names;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_name_recycler, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindHolder: FROM mNAMES was called");
        holder.name.setText(mNames.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "THE INGREDIENT LISTENER WAS CLICEKD");
                Intent intent = new Intent(mContext, DrinkDisplayActivity.class);
                intent.putExtra(IDNUMBER, mIDs.get(position));
                intent.putExtra(NAME, mNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout parent;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_name_holder);
            parent = itemView.findViewById(R.id.ingredient_name_holder_parent);
        }
    }
}

