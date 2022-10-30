package com.example.madoriginal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LyricsViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public View view;


    public LyricsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.image_single_view);
        textView= itemView.findViewById(R.id.lyricsText_singleView);
        view=itemView;




    }
}
