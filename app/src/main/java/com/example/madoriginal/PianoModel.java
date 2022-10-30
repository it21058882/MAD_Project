package com.example.madoriginal;

public class PianoModel {
    private String PianoName;
    private String ImageUrl;

    public PianoModel(String pianoName, String imageUrl) {
        PianoName = pianoName;
        ImageUrl = imageUrl;
    }

    public PianoModel() {
    }

    public String getPianoName() {
        return PianoName;
    }

    public void setPianoName(String pianoName) {
        PianoName = pianoName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}


