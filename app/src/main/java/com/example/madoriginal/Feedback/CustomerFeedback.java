package com.example.madoriginal.Feedback;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madoriginal.R;

public class CustomerFeedback extends AppCompatActivity {

    Button button1;
    Button button2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);

        button1 = findViewById(R.id.addfeedback);
        button2 = findViewById(R.id.viewfeedback);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFeedback.this, AddFeedback.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFeedback.this, ViewFeedBack.class);
                startActivity(intent);
            }
        });
    }
}