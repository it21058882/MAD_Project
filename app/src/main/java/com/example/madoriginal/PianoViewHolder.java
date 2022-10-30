package com.example.madoriginal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PianoViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public View view;


    public PianoViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.Piano_single_view);
        textView= itemView.findViewById(R.id.PianoText_singleView);
        view=itemView;




    }
}