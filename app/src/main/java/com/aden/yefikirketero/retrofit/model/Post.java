package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public Post(String userId, String name, int age, String gender, String phone, String Bio, String is_profile_posted) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.Bio = Bio;
        this.is_profile_posted = is_profile_posted;

    }


    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private int age;

    @SerializedName("gender")
    private String gender;

    @SerializedName("phone_number")
    private String phone;

    @SerializedName("bio")
    private String Bio;

    @SerializedName("is_profile_posted")
    private String is_profile_posted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getIs_profile_posted() {
        return is_profile_posted;
    }

    public void setIs_profile_posted(String is_profile_posted) {
        this.is_profile_posted = is_profile_posted;
    }
}