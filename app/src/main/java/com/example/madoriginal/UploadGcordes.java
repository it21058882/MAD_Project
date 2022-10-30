package com.example.madoriginal;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import com.example.madoriginal.Contibuter.GcodeContributer;
import com.example.madoriginal.Contibuter.LyricsContributer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UploadGcordes extends AppCompatActivity {

    private ImageView GimageViewAdd;
    private EditText GeditText,Gdescription;
    private TextView GtextViewProgress;
    private ProgressBar GprogressBar;
    private Button GuploadBtn;
    private static final int REQUEST_CODE_IMAGE=101;
    Uri imageUri;
    boolean isImageAdded=false;

    DatabaseReference Dataref;
    StorageReference storageReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gcordes);

        GimageViewAdd=findViewById(R.id.GCodeView);
        GeditText=findViewById(R.id.inputGImageName);
        GtextViewProgress=findViewById(R.id.textViewProgress);
        GprogressBar=findViewById(R.id.GprogressBar);
        GuploadBtn=findViewById(R.id.btnUpload);
        Gdescription=findViewById(R.id.inputDescription);




        GtextViewProgress.setVisibility(View.GONE);
        GprogressBar.setVisibility(View.GONE);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Guitar Chords");
        storageReference= FirebaseStorage.getInstance().getReference().child("ChordsImage");

        GimageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);


            }
        });

        GuploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String imageName= GeditText.getText().toString();
                final String DesName= Gdescription.getText().toString();
                if (isImageAdded && imageName!=null){
                    uploadImage(imageName,DesName);
                }
            }
        });

    }

    private void uploadImage(final String imageName,final String DesName) {

        GtextViewProgress.setVisibility(View.VISIBLE);
        GprogressBar.setVisibility(View.VISIBLE);
        final String key=Dataref.push().getKey();
        storageReference.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap<>();
                        hashMap.put("Description",DesName);
                        hashMap.put("GuitarChordsName",imageName);
                        hashMap.put("GImageUrl",uri.toString());

                        Dataref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UploadGcordes.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), GcodeContributer.class));
                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                GprogressBar.setProgress((int) progress);
                GtextViewProgress.setText(progress+"%");
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==REQUEST_CODE_IMAGE&& data!=null ){

            imageUri=data.getData();
            isImageAdded=true;
            GimageViewAdd.setImageURI(imageUri);
        }

    }
}