package com.example.madoriginal;

public class GcodeModel {
    private String GcodeName;
    private String GImageUrl;

    public GcodeModel(String gcodeName, String GimageUrl) {
        GcodeName = gcodeName;
        GImageUrl = GimageUrl;
    }

    public GcodeModel() {
    }

    public String getGcodeName() {
        return GcodeName;
    }

    public void setGcodeName(String gcodeName) {
        GcodeName = gcodeName;
    }

    public String getGImageUrl() {
        return GImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        GImageUrl = imageUrl;
    }
}


