package com.example.madoriginal.Feedback.Models;

public class Feedbacks {

    String id;
    String name;
    String email;
    String suggession;

    public Feedbacks() {
    }

    public Feedbacks(String id, String name, String email, String suggession) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.suggession = suggession;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuggession() {
        return suggession;
    }

    public void setSuggession(String suggession) {
        this.suggession = suggession;
    }
}
