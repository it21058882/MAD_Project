package com.example.madoriginal.VideoView;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madoriginal.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.HolderVideo> {

    private Context context;
    private ArrayList<VideoModel> videoArrayList;

    public VideoAdapter(Context context, ArrayList<VideoModel> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_video,parent,false);
        return new HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        VideoModel videoModel =videoArrayList.get(position);
        String title=videoModel.getVideosName();
        String videoUrl=videoModel.getVideoUrl();



        holder.title.setText(title);

        setVideoUrl(videoModel,holder);

    }

    private void setVideoUrl(VideoModel videoModel,HolderVideo holder){

        holder.progressBar.setVisibility(View.VISIBLE);
        String videoUrl=videoModel.getVideoUrl();

        MediaController mediaController=new MediaController(context);
        mediaController.setAnchorView(holder.videoView);

        Uri videoUri=Uri.parse(videoUrl);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setVideoURI(videoUri);

        holder.videoView.requestFocus();
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        holder.videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return true;
                    }
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:{

                        holder.progressBar.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });
        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HolderVideo extends RecyclerView.ViewHolder{
        VideoView videoView;
        TextView title;
        ProgressBar progressBar;
        View view;


        public HolderVideo(@NonNull View itemView) {
            super(itemView);
             view = itemView;
            videoView=itemView.findViewById(R.id.videodisplay);
            title=itemView.findViewById(R.id.titlevideo);
            progressBar=itemView.findViewById(R.id.progvideo);




        }
    }
}
