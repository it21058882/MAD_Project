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

public class PianoDisplay extends AppCompatActivity {

    EditText lyricsSearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PianoModel> options;
    FirebaseRecyclerAdapter<PianoModel,PianoViewHolder> adapter;
    DatabaseReference Dataref;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_display);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Piano Chords");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Piano");
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
        Query query=Dataref.orderByChild("PianoName").startAt(data).endAt(data+"\uf8ff");

        options=new FirebaseRecyclerOptions.Builder<PianoModel>().setQuery(query,PianoModel.class).build();
        adapter=new FirebaseRecyclerAdapter<PianoModel, PianoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PianoViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull PianoModel model) {

                holder.textView.setText(model.getPianoName());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count=0;

                        Intent intent=new Intent(PianoDisplay.this,FullViewPiano.class);

//                        Dataref.updateChildren("View Count",)
                        intent.putExtra("PianoKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public PianoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleviewpiano,parent,false);

                return new PianoViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}