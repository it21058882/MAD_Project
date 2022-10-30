package com.example.madoriginal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class FullViewGcodes extends AppCompatActivity {

    private ImageView imageView;
    TextView textView,des;
    Button btnDlt ,update;

    DatabaseReference ref,DataRef;
    StorageReference storageReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_gcodes);

        imageView=findViewById(R.id.GFullView_Activity);
        textView=findViewById(R.id.GFullView_Activity_textview);
        update=findViewById(R.id.updateGcode);
        des=findViewById(R.id.Gcodedes);
        btnDlt=findViewById(R.id.GlycDlt);
        ref= FirebaseDatabase.getInstance().getReference().child("Guitar Chords");

        String GuitarKey=getIntent().getStringExtra("GuitarKey");
        DataRef=FirebaseDatabase.getInstance().getReference().child("Guitar Chords").child(GuitarKey);
        storageReference= FirebaseStorage.getInstance().getReference().child("ChordsImage").child(GuitarKey+".jpg");




        ref.child(GuitarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    String GcodeName=snapshot.child("GuitarChordsName").getValue().toString();
                    String GcodeDes=snapshot.child("GuitarChordsName").getValue().toString();
                    String GcodeUrl=snapshot.child("GImageUrl").getValue().toString();
                    Picasso.get().load(GcodeUrl).into(imageView);
                    textView.setText("Chord Name :"+" "+GcodeName);
                    des.setText("Descrption :"+""+GcodeDes);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDlt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startActivity(new Intent(getApplicationContext(),GcodeDisplay.class));
                            }
                        });
                    }
                });

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        startActivity(new Intent(getApplicationContext(),UploadGcordes.class));

                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startActivity(new Intent(getApplicationContext(),UploadGcordes.class));
                            }
                        });
                    }
                });

            }
        });



    }
}