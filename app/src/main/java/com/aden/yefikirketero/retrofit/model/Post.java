package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {

    public Post(String userId, String phoneNumber, String name, String age, String bio, String isProfilePosted, String registeringTime, String sessionId) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.isProfilePosted = isProfilePosted;
        this.registeringTime = registeringTime;
        this.sessionId = sessionId;
    }


    @SerializedName("_id")
    private String userId;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private String age;

    @SerializedName("bio")
    private String bio;

    @SerializedName("is_profile_posted")
    private String isProfilePosted;

    @SerializedName("registering_time")
    private String registeringTime;

    @SerializedName("session_id")
    private String sessionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getIsProfilePosted() {
        return isProfilePosted;
    }

    public void setIsProfilePosted(String isProfilePosted) {
        this.isProfilePosted = isProfilePosted;
    }

    public String getRegisteringTime() {
        return registeringTime;
    }

    public void setRegisteringTime(String registeringTime) {
        this.registeringTime = registeringTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}