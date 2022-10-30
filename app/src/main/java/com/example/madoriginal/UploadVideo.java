package com.example.madoriginal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UploadVideo extends AppCompatActivity {

    private ActionBar actionBar;
    private EditText editText;
    private VideoView videoView;
    private Button uploadbtn;
    private static final int PICK_VIDEO=1;

    DatabaseReference Dataref;
    StorageReference storageReference;
    Uri VideoUri;
    boolean isImageAdded=false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Add New Video");
        actionBar.setDisplayHomeAsUpEnabled(true);

      editText=findViewById(R.id.inputVideoName);
        videoView=findViewById(R.id.VideoViewAdd);
        uploadbtn=findViewById(R.id.btnVidUpload);
//        progressBar.setVisibility(View.GONE);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Videos");
        storageReference= FirebaseStorage.getInstance().getReference().child("VideoImages");

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String videNmae= editText.getText().toString();

                if (isImageAdded && videNmae!=null){
                    uploadVideo( videNmae);
                }



            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_VIDEO);


            }
        });




    }

    private void uploadVideo(String videoNmae) {

//        ;
//        progressBar.setVisibility(View.VISIBLE);


        final String key=Dataref.push().getKey();
        storageReference.child(key+".mp4").putFile(VideoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.child(key+".mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap<>();
                        hashMap.put("AudioName",videoNmae);
                        hashMap.put("AudioUrl",uri.toString());

                        Dataref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UploadVideo.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

//                double progress=(taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
//                progressBar.setProgress((int) progress);

            }
        });

    }

    private void setVideotoVideoView(){

        MediaController mediaController =new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(VideoUri);
        videoView.requestFocus();






    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==PICK_VIDEO&& data!=null ){

            VideoUri=data.getData();
            isImageAdded=true;
            setVideotoVideoView();

        }

    }
}