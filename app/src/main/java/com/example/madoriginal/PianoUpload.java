package com.example.madoriginal;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madoriginal.Contibuter.LyricsContributer;
import com.example.madoriginal.Contibuter.VideoContributer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class PianoUpload extends AppCompatActivity {

    private ImageView imageViewAdd;
    private EditText editText,descrptiontxt;
    private TextView textViewProgress;
    private ProgressBar progressBar;
    private Button uploadBtn;
    private ActionBar actionBar;
    private static final int REQUEST_CODE_IMAGE=101;
    Uri imageUri;
    boolean isImageAdded=false;

    DatabaseReference Dataref;
    StorageReference storageReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_upload);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Add Piano Chords");
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageViewAdd=findViewById(R.id.imageViewAdd);
        editText=findViewById(R.id.inputImageName);
        descrptiontxt=findViewById(R.id.inputDescription);
        textViewProgress=findViewById(R.id.textViewProgress);
        progressBar=findViewById(R.id.progressBar);
        uploadBtn=findViewById(R.id.btnUpload);

        textViewProgress.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Piano");
        storageReference= FirebaseStorage.getInstance().getReference().child("PianoImage");

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);


            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String imageName= editText.getText().toString();
                final String DesName= descrptiontxt.getText().toString();

                if (isImageAdded && imageName!=null){
                    uploadImage(imageName,DesName);
                }
            }
        });

    }

    private void uploadImage(final String imageName, final String DesName) {

        textViewProgress.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        final String key=Dataref.push().getKey();
        storageReference.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                int count=0;
                storageReference.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap<>();
                        hashMap.put("PianoName",imageName);
                        hashMap.put("Description",DesName);
                        hashMap.put("ImageUrl",uri.toString());
                        hashMap.put("View Count", count);

                        Dataref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(PianoUpload.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), VideoContributer.class));
                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
                textViewProgress.setText(progress+"%");
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==REQUEST_CODE_IMAGE&& data!=null ){

            imageUri=data.getData();
            isImageAdded=true;
            imageViewAdd.setImageURI(imageUri);
        }

    }
}