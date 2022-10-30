package com.example.madoriginal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madoriginal.CustomerViews.CustomerDisplayGcode;
import com.example.madoriginal.CustomerViews.CustomerDisplayLyrics;
import com.example.madoriginal.CustomerViews.CustomerPianoView;
import com.example.madoriginal.Feedback.CustomerFeedback;
import com.example.madoriginal.VideoView.VideoDisplay;

public class CutomerMenu extends AppCompatActivity {

    Button showvid,showlyrics,showgcodes,sugbox,login;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_menu);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Chill Box");
        actionBar.setDisplayHomeAsUpEnabled(true);


//        showgcodes.findViewById(R.id.gutshow);
        Button gcodes=(Button)findViewById(R.id.gutshow);
        Button showvid=(Button)findViewById(R.id.vidshow);
        Button showlyrics=(Button)findViewById(R.id.lycshow);
        Button sugbox=(Button)findViewById(R.id.sgbshow);
        Button login=(Button)findViewById(R.id.login);


//        showvid.findViewById(R.id.vidshow);
//        showlyrics.findViewById(R.id.lycshow);
//        sugbox.findViewById(R.id.sgbshow);
//        login.findViewById(R.id.login);

        showvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CutomerMenu.this, CustomerPianoView.class));
            }
        });
        gcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CutomerMenu.this, CustomerDisplayGcode.class));
            }
        });
        showlyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CutomerMenu.this, CustomerDisplayLyrics.class));
            }
        });
        sugbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CutomerMenu.this, CustomerFeedback.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CutomerMenu.this,LogginActivity.class));
            }
        });




    }
}