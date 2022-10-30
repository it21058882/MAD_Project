package com.example.madoriginal;

public class LyricsModel {
    private String LyricsName;
    private String ImageUrl;


    public LyricsModel(String lyricsName, String imageUrl) {
        LyricsName = lyricsName;
        ImageUrl = imageUrl;
    }

    public LyricsModel() {
    }

    public String getLyricsName() {
        return LyricsName;
    }

    public void setLyricsName(String lyricsName) {
        LyricsName = lyricsName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}


