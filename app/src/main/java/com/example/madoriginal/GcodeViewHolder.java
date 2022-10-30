package com.example.madoriginal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GcodeViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView gtextView;
    public View view;





    public GcodeViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView= itemView.findViewById(R.id.Gimage_single_view);
        gtextView= itemView.findViewById(R.id.GText_singleView);
        view=itemView;
    }
}
