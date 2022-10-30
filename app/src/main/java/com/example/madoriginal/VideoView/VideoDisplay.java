package com.example.madoriginal.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.madoriginal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<VideoModel> arrayList;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        setTitle("Videos");

        recyclerView=findViewById(R.id.recyclerView);

        loadVideos();


    }

    private void loadVideos(){
        arrayList=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Videos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds :snapshot.getChildren()){

                    VideoModel videoModel=ds.getValue(VideoModel.class);
                    arrayList.add(videoModel);
                }
                videoAdapter=new VideoAdapter(VideoDisplay.this,arrayList);

                recyclerView.setAdapter(videoAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}