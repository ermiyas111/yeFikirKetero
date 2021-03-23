package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {
    public Person(String phoneNumber, List<Post> peopleLiked, List<Post> likedBy, List<Post> phoneNotifications) {
        this.phoneNumber = phoneNumber;
        this.peopleLiked = peopleLiked;
        this.likedBy = likedBy;
        this.phoneNotifications = phoneNotifications;
    }


    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("people_liked")
    private List<Post> peopleLiked;

    @SerializedName("liked_by")
    private List<Post> likedBy;

    @SerializedName("phone_notifications")
    private List<Post> phoneNotifications;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Post> getPeopleLiked() {
        return peopleLiked;
    }

    public void setPeopleLiked(List<Post> peopleLiked) {
        this.peopleLiked = peopleLiked;
    }

    public List<Post> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<Post> likedBy) {
        this.likedBy = likedBy;
    }

    public List<Post> getPhoneNotifications() {
        return phoneNotifications;
    }

    public void setPhoneNotifications(List<Post> phoneNotifications) {
        this.phoneNotifications = phoneNotifications;
    }
}
