package com.rememberthekey.cocktail.utility.RecyclerAdapters;

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

import com.rememberthekey.cocktail.DrinkDisplayActivity;
import com.rememberthekey.cocktail.R;
import com.rememberthekey.cocktail.model.CockTailModel;

import java.util.List;

public class RecyclerViewIngredientAdapter extends RecyclerView.Adapter<RecyclerViewIngredientAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewIngredientA";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private List<CockTailModel> mDataset;
    private Context mContext;


    public RecyclerViewIngredientAdapter (Context context, List<CockTailModel> dataset) {
        this.mContext = context;
        this.mDataset = dataset;
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
        holder.name.setText(mDataset.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "THE INGREDIENT LISTENER WAS CLICEKD");
                Intent intent = new Intent(mContext, DrinkDisplayActivity.class);
                intent.putExtra(IDNUMBER, mDataset.get(position).getId());
                intent.putExtra(NAME, mDataset.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
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

