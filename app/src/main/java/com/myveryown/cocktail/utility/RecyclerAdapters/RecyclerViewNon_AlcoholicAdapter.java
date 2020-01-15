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

public class RecyclerViewNon_AlcoholicAdapter  extends RecyclerView.Adapter<RecyclerViewNon_AlcoholicAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewNon_Alcohol";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private ArrayList<String> id_drinks;
    private ArrayList<String> name_drinks;
    private Context mContext;

    public RecyclerViewNon_AlcoholicAdapter(Context context, ArrayList<String> names, ArrayList<String> id) {
        this.mContext = context;
        this.id_drinks = id;
        this.name_drinks = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.non_alcoholic_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"OnBindViewHolder: IT GOT CALLED FOR THE NON ALCOHOLIC");
        holder.name.setText(name_drinks.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DrinkDisplayActivity.class);
                intent.putExtra(IDNUMBER, id_drinks.get(position));
                intent.putExtra(NAME, name_drinks.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name_drinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.non_alcoholic_layout_container);
            this.parent = itemView.findViewById(R.id.non_alcoholic_layout_container_parent);
        }
    }
}
