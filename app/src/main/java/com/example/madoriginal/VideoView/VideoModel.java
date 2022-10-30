package com.example.madoriginal.VideoView;

public class VideoModel {

    String VideosName,VideoUrl;

    public VideoModel() {
    }

    public VideoModel(String videosName, String videoUrl) {
        VideosName = videosName;
        VideoUrl = videoUrl;
    }

    public String getVideosName() {
        return VideosName;
    }

    public void setVideosName(String videosName) {
        VideosName = videosName;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }
}
