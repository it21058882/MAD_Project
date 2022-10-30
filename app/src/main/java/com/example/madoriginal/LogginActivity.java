package com.example.madoriginal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.madoriginal.Contibuter.ContributorMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logbtn;
    EditText email;
    EditText password;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.emailedit);
        password =findViewById(R.id.editTextPassword);
        logbtn = findViewById(R.id.logbtn);

        auth= FirebaseAuth.getInstance();


        logbtn.setOnClickListener(view -> {

            loguser();
        });



    }




    private void loguser(){

        String Email=email.getText().toString();
        String Password = password.getText().toString();

        if(TextUtils.isEmpty(Email)){
            email.setError("Email Can not Empty");
            email.requestFocus();
        }else if (TextUtils.isEmpty(Password)){

            password.setError("Password Can not be Empty");
            password.requestFocus();

        }else {

            auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LogginActivity.this,"Succesfully Loggedin",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogginActivity.this,ContributorMenu.class));
                    }else {
                        Toast.makeText(LogginActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                    }


                }
            });

        }


    }


}