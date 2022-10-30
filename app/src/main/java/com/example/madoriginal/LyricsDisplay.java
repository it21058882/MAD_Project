package com.example.madoriginal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class LyricsDisplay extends AppCompatActivity {

    EditText lyricsSearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<LyricsModel> options;
    FirebaseRecyclerAdapter<LyricsModel,LyricsViewHolder> adapter;
    DatabaseReference Dataref;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_display);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Lyrics");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Lyrics");
        lyricsSearch=findViewById(R.id.search_lyrics);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData("");
        lyricsSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString()!=null){

                    LoadData(editable.toString());
                }else {
                    LoadData("");
                }

            }
        });
    }

    private void LoadData(String data) {
        Query query=Dataref.orderByChild("LyricsName").startAt(data).endAt(data+"\uf8ff");

        options=new FirebaseRecyclerOptions.Builder<LyricsModel>().setQuery(query,LyricsModel.class).build();
        adapter=new FirebaseRecyclerAdapter<LyricsModel, LyricsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LyricsViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull LyricsModel model) {

                holder.textView.setText(model.getLyricsName());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count=0;

                        Intent intent=new Intent(LyricsDisplay.this,FullViewLyrics.class);

//                        Dataref.updateChildren("View Count",)
                        intent.putExtra("LyricsKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public LyricsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);

                return new LyricsViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}