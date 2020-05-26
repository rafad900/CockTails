package com.rememberthekey.cocktail.utility.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rememberthekey.cocktail.DrinkDisplayActivity;
import com.rememberthekey.cocktail.R;
import com.rememberthekey.cocktail.model.CockTailModel;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyNameRecyclerAdapter extends RecyclerView.Adapter<MyNameRecyclerAdapter.MyViewHolder> {
    private List<CockTailModel> mDataset;
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout parentLayout;
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            parentLayout = v.findViewById(R.id.name_drink_recycler_text_view_parent);
            textView = v.findViewById(R.id.name_drink_recycler_text_view);
        }
    }

    public MyNameRecyclerAdapter(Context context, List<CockTailModel> mDataset) {
        this.mDataset = mDataset;
        this.mContext = context;
    }

    @Override
    public MyNameRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_drink_recycler_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d(TAG, "OnBindViewHolder was called");
        holder.textView.setText(mDataset.get(position).getName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
