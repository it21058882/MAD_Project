package com.example.madoriginal.Contibuter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madoriginal.AddMember;
import com.example.madoriginal.CutomerMenu;
import com.example.madoriginal.Feedback.AdminFeedback;
import com.example.madoriginal.Feedback.CustomerFeedback;
import com.example.madoriginal.Feedback.ViewFeedBack;
import com.example.madoriginal.GcodeDisplay;
import com.example.madoriginal.LogginActivity;
import com.example.madoriginal.LyricsDisplay;
import com.example.madoriginal.R;
import com.example.madoriginal.VideoView.VideoDisplay;
import com.example.madoriginal.count.ItemCounts;
import com.google.firebase.auth.FirebaseAuth;

public class ContributorMenu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor_menu);

        firebaseAuth=FirebaseAuth.getInstance();


        actionBar=getSupportActionBar();
        actionBar.setTitle("Contributor Menu");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button gcodes=(Button)findViewById(R.id.gutshow);
        Button showvid=(Button)findViewById(R.id.vidshow);
        Button showlyrics=(Button)findViewById(R.id.lycshow);
        Button sugbox=(Button)findViewById(R.id.sgbshow);
        Button addmem=(Button)findViewById(R.id.addmember);
        Button vcnt=(Button)findViewById(R.id.viewcountall);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button Logout=(Button)findViewById(R.id.LogOut) ;




        showvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributorMenu.this, VideoContributer.class));
            }
        });
        gcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributorMenu.this, GcodeContributer.class));
            }
        });
        showlyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributorMenu.this, LyricsContributer.class));
            }
        });
        sugbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributorMenu.this, ViewFeedBack.class));
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributorMenu.this, CutomerMenu.class));
            }
        });

        addmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity(new Intent(ContributorMenu.this, AddMember.class));
            }
        });

        vcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity(new Intent(ContributorMenu.this, ItemCounts.class));
            }
        });




    }
}