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

import java.util.ArrayList;

public class RecyclerViewDrinkNameAdapter extends RecyclerView.Adapter<RecyclerViewDrinkNameAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewDrinkNameAd";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private ArrayList<String> mNames;
    private Context mContext;
    private ArrayList<String> mID;

    public RecyclerViewDrinkNameAdapter (Context context, ArrayList<String> names, ArrayList<String> id) {
        this.mID = id;
        this.mContext = context;
        this.mNames = names;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This inflates the layout that you want to repeat
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_drink_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // This is what fills that layout once its inflated
        Log.d(TAG, "onBindHolder: FROM NAME WAS CALLED");
        holder.name.setText(mNames.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "NAME DRINK listener was clicked");
                Intent intent = new Intent(mContext, DrinkDisplayActivity.class);
                intent.putExtra(IDNUMBER, mID.get(position));
                intent.putExtra(NAME, mNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // remmeber to always have this return soemthing
        // preferallby a number and more specifically the size of the
        // list of items that you want to show in the layout
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // This view holder is how the items are going to be set up
        // in the layout you created. It is what tells what to go
        // where. Before making this class, the onCreateViewHolder uses
        // its own viewholder class which is obviously useless to us
        // since it doesn't know our layout.
        TextView name;
        RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_drink_recycler_text_view);
            parent = itemView.findViewById(R.id.name_drink_recycler_text_view_parent);
        }
    }
}
