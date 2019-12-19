package com.example.cocktail.utility.RecyclerAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail.R;

import java.util.ArrayList;

public class RecyclerViewIngredientAdapter extends RecyclerView.Adapter<RecyclerViewIngredientAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewIngredientA";

    private ArrayList<String> mNames;
    private Context mContext;

    public RecyclerViewIngredientAdapter (Context context, ArrayList<String> names) {
        this.mContext = context;
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
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
                /* todo I have to add the intent to the drink display activity here*/
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

