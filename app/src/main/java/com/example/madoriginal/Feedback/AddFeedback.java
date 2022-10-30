package com.example.madoriginal.Feedback;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.madoriginal.Feedback.Models.Feedbacks;

import androidx.appcompat.app.AppCompatActivity;


import com.example.madoriginal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedback extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;
    DatabaseReference ref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.email);
        editText3 = findViewById(R.id.message);
        button = findViewById(R.id.addfeedback);

        ref = FirebaseDatabase.getInstance().getReference("Feedbacks");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText1.getText().toString();
                String email = editText2.getText().toString();
                String message = editText3.getText().toString();

                if (name.isEmpty()) {
                    editText1.setError("Please enter your Name");
                }else if (email.isEmpty()) {
                    editText2.setError("Insert your Email");
                }else if (message.isEmpty()) {
                    editText3.setError("Add your Feedback");
                }else{
                    String key = ref.push().getKey();

                    Feedbacks feedbacks = new Feedbacks(key,name,email,message);
                    ref.child(key).setValue(feedbacks);

                    Toast.makeText(AddFeedback.this, "Successfully added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddFeedback.this, CustomerFeedback.class);
                    startActivity(intent);
                }
            }
        });
    }
}