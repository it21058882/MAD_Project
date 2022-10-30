package com.example.madoriginal.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madoriginal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ItemCounts extends AppCompatActivity {

    private ImageView imageView;
    TextView lyccnt,gcnt,vidcnt;
    Button btnDlt,uptbtn;

    private int count;

    DatabaseReference ref,cref,cc,cb,cv;

    StorageReference countref;
    StorageReference storageReference;
    ActionBar actionBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_counts);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Item Counts");
        actionBar.setDisplayHomeAsUpEnabled(true);



        lyccnt=findViewById(R.id.countlyc);
        gcnt=findViewById(R.id.countGcode);
        vidcnt=findViewById(R.id.countVid);

        ref= FirebaseDatabase.getInstance().getReference().child("Lyrics");


        countref= FirebaseStorage.getInstance().getReference().child("LyricsImage");

        cc=FirebaseDatabase.getInstance().getReference().child("Lyrics");
        cb=FirebaseDatabase.getInstance().getReference().child("Guitar Chords");
        cv=FirebaseDatabase.getInstance().getReference().child("Videos");

        String LyricsKey=getIntent().getStringExtra("LyricsKey");
        String Lyrics=getIntent().getStringExtra("Lyrics");
//        DataRef=FirebaseDatabase.getInstance().getReference().child("Lyrics").child(LyricsKey);
        storageReference= FirebaseStorage.getInstance().getReference().child("LyricsImage").child(LyricsKey+".jpg");


        cc.getDatabase().getReference().child("Lyrics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    count=(int) snapshot.getChildrenCount();
                    lyccnt.setText("Full Lyrics Count : "+(Integer.toString(count)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cb.getDatabase().getReference().child("Guitar Chords").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    count=(int) snapshot.getChildrenCount();
                    gcnt.setText("Full Guitar Chord Count : "+(Integer.toString(count)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        cv.getDatabase().getReference().child("Videos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    count=(int) snapshot.getChildrenCount();
                    vidcnt.setText("Full Video Count :"+(Integer.toString(count)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




}}