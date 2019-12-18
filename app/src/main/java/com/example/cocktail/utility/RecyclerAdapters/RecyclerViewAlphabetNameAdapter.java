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

public class RecyclerViewAlphabetNameAdapter extends RecyclerView.Adapter<RecyclerViewAlphabetNameAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAlphabetNam";

    private ArrayList<String> mAlphabet = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAlphabetNameAdapter(Context context, ArrayList<String> alphabet) {
        this.mContext = context;
        this.mAlphabet = alphabet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout that you want to be repeated
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_recycler_name_activity, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.letter.setText(mAlphabet.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick LISTENER was clicked with letter: " + mAlphabet.get(position));
                Toast.makeText(mContext, mAlphabet.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlphabet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView letter;
        RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.alphaLetterRecycler);
            parent = itemView.findViewById(R.id.alphaLetterRecycler_parentLayout);
        }
    }
}
