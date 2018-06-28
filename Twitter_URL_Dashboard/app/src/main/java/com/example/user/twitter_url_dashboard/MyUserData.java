package com.example.user.twitter_url_dashboard;

import java.util.ArrayList;

public class MyUserData {

    private String time;
    private String username;
    private ArrayList<String> urls;

    public MyUserData(String time, String username, ArrayList<String> urls) {
        this.time = time;
        this.username = username;
        this.urls = urls;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
}
