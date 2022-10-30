package com.example.madoriginal;

import androidx.annotation.NonNull;
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

public class GcodeDisplay extends AppCompatActivity {

    EditText gcodeSearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<GcodeModel> options;
    FirebaseRecyclerAdapter<GcodeModel,GcodeViewHolder> adapter;
    DatabaseReference Dataref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcode_display);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Guitar Chords");
       gcodeSearch=findViewById(R.id.search_gcode);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData("");

        gcodeSearch.addTextChangedListener(new TextWatcher() {
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

        Query query=Dataref.orderByChild("GuitarChordsName").startAt(data).endAt(data+"\uf8ff");


        options=new FirebaseRecyclerOptions.Builder<GcodeModel>().setQuery(query,GcodeModel.class).build();
        adapter=new FirebaseRecyclerAdapter<GcodeModel, GcodeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GcodeViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull GcodeModel model) {


                holder.gtextView.setText(model.getGcodeName());
                Picasso.get().load(model.getGImageUrl()).into(holder.imageView);
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(GcodeDisplay.this,FullViewGcodes.class);
                        intent.putExtra("GuitarKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public GcodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_gcodes,parent,false);

                return new GcodeViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}