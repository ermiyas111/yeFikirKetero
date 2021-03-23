package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostUpload {

    public PostUpload(long phoneNumber, String name, int age, String bio, String isProfilePosted, int registeringTime, int sessionId) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.isProfilePosted = isProfilePosted;
        this.registeringTime = registeringTime;
        this.sessionId = sessionId;
    }


    @SerializedName("phone_number")
    private long phoneNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private int age;

    @SerializedName("bio")
    private String bio;

    @SerializedName("is_profile_posted")
    private String isProfilePosted;

    @SerializedName("registering_time")
    private int registeringTime;

    @SerializedName("session_id")
    private int sessionId;

    //@SerializedName("phone_notifications")
    //private List<Post> phoneNotifications;

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public int getRegisteringTime() {
        return registeringTime;
    }

    public void setRegisteringTime(int registeringTime) {
        this.registeringTime = registeringTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}