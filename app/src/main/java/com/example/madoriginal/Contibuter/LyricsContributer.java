package com.example.madoriginal.Contibuter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madoriginal.LyricsDisplay;
import com.example.madoriginal.R;
import com.example.madoriginal.UploadLy;
import com.example.madoriginal.UploadVideo;
import com.example.madoriginal.VideoView.VideoDisplay;

public class LyricsContributer extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_contributer);

        Button upload=(Button)findViewById(R.id.viduplodactivity);
        Button Delete=(Button)findViewById(R.id.viddltactivity);
        Button Update=(Button)findViewById(R.id.vidupdateactivity);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LyricsContributer.this, UploadLy.class));
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LyricsContributer.this, LyricsDisplay.class));
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LyricsContributer.this, LyricsDisplay.class));
            }
        });



    }
}