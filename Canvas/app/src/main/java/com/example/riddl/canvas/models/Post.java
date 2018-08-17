package com.example.riddl.canvas.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {
    private String username, userImg, postImg, timeStamp, description;
    private int likesNumber;

    public Post() {
    }

    public Post(String username, String userImg, String postImg, String description, int likesNumber, String timeStamp) {
        this.username = username;
        this.userImg = userImg;
        this.postImg = postImg;
        this.likesNumber = likesNumber;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public String getTimeStamp() { return timeStamp; }

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }

    public String getDescription() { return description;    }

    public void setDescription(String description) { this.description = description;    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("timeStamp", timeStamp);
        result.put("userImg", userImg);
        result.put("description", description);
        result.put("likesNumber", likesNumber);
        result.put("postImg", postImg);

        return result;
    }
}