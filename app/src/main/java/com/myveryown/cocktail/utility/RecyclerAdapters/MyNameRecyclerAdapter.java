package com.myveryown.cocktail.utility.RecyclerAdapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.myveryown.cocktail.R;
import com.myveryown.cocktail.model.CockTailModel;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyNameRecyclerAdapter extends RecyclerView.Adapter<MyNameRecyclerAdapter.MyViewHolder> {
    private List<CockTailModel> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout parentLayout;
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            parentLayout = v.findViewById(R.id.name_drink_recycler_text_view_parent);
            textView = v.findViewById(R.id.name_drink_recycler_text_view);
        }
    }

    public MyNameRecyclerAdapter(List<CockTailModel> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public MyNameRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_drink_recycler_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder was called");
        holder.textView.setText(mDataset.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
