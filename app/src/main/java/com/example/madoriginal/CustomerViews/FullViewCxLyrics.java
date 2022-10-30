package com.example.madoriginal.CustomerViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madoriginal.FullViewLyrics;
import com.example.madoriginal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class FullViewCxLyrics extends AppCompatActivity {

    private ImageView imageView;
    TextView textView,desview,viewcnt;
    Button btnDlt,uptbtn;

    private int count;

    DatabaseReference ref,cref,cc;
    DatabaseReference DataRef;
    StorageReference countref;
    StorageReference storageReference;
    ActionBar actionBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_cx_lyrics);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Lyrics");
        actionBar.setDisplayHomeAsUpEnabled(true);


        imageView=findViewById(R.id.FullView_Activity);
        desview=findViewById(R.id.lycdes);

        textView=findViewById(R.id.FullView_Activity_textview);
        btnDlt=findViewById(R.id.lycDlt);
        uptbtn=findViewById(R.id.updatelyc);
        ref= FirebaseDatabase.getInstance().getReference().child("Lyrics");


        countref=FirebaseStorage.getInstance().getReference().child("LyricsImage");

        cc=FirebaseDatabase.getInstance().getReference().child("Lyrics");

        String LyricsKey=getIntent().getStringExtra("LyricsKey");
        String Lyrics=getIntent().getStringExtra("Lyrics");
        DataRef=FirebaseDatabase.getInstance().getReference().child("Lyrics").child(LyricsKey);
        storageReference= FirebaseStorage.getInstance().getReference().child("LyricsImage").child(LyricsKey+".jpg");






        ref.child(LyricsKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String viewcount=snapshot.child("View Count").getValue().toString();
                    String Descrption =snapshot.child("Description").getValue().toString();
                    String LyricsName=snapshot.child("LyricsName").getValue().toString();
                    String ImageUrl=snapshot.child("ImageUrl").getValue().toString();

                    Picasso.get().load(ImageUrl).into(imageView);
                    textView.setText("Lyrics Title :"+" "+LyricsName);
                    desview.setText("Lyrics Description :"+" "+Descrption);






                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




}}