package com.rememberthekey.cocktail.utility.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rememberthekey.cocktail.DrinkDisplayActivity;
import com.rememberthekey.cocktail.R;
import com.rememberthekey.cocktail.model.CockTailModel;

import java.util.List;

public class RecyclerViewNon_AlcoholicAdapter  extends RecyclerView.Adapter<RecyclerViewNon_AlcoholicAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewNon_Alcohol";
    private static final String IDNUMBER = "ID_NUMBER";
    private static final String NAME = "DRINK_NAME";

    private List<CockTailModel> mDataset;
    private Context mContext;

    public RecyclerViewNon_AlcoholicAdapter(Context context, List<CockTailModel> dataset) {
        this.mContext = context;
        this.mDataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_drink_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG,"OnBindViewHolder: IT GOT CALLED FOR THE NON ALCOHOLIC");
        holder.name.setText(mDataset.get(position).getName());
        holder.image.setImageBitmap(mDataset.get(position).getImage());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        View parent;
        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.parent = itemView.findViewById(R.id.general_drink_parent);
            this.name = itemView.findViewById(R.id.general_drink_name);
            this.image = itemView.findViewById(R.id.general_drink_image);
        }
    }
}
