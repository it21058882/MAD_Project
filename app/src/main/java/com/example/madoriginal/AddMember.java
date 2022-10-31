package com.example.madoriginal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AddMember extends AppCompatActivity {
// minidu edited

    Button addmember;
    EditText Name;
    EditText Email;
    EditText Password;
    EditText rePassword;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        addmember=findViewById(R.id.addmemberbtn);
        Email = findViewById(R.id.emailadd);
        Password =findViewById(R.id.textPassword);
        rePassword = findViewById(R.id.textPassword2);

        auth = FirebaseAuth.getInstance();

        addmember.setOnClickListener(view -> {

            addMember();
        });



    }

    private void addMember(){

        String email=Email.getText().toString();
        String password=Password.getText().toString();
        String repassword=rePassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Email.setError("Email can not be Empty");
            Email.requestFocus();
        }else if (TextUtils.isEmpty(password)){

            Password.setError("Password can not be Empty");
            Password.requestFocus();
        }else if (TextUtils.isEmpty(repassword)) {

            rePassword.setError("Password can not be Empty");
            rePassword.requestFocus();
        }else if (!password.equals(repassword)){

            Toast.makeText(AddMember.this,"Passwords need to be same",Toast.LENGTH_SHORT).show();
        }else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AddMember.this,"Member Succesfully Regitered",Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(AddMember.this,"Error"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }
}