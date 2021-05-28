package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostUpload {

    public PostUpload(long phoneNumber, String name, int age, String gender, String religion, String city, String height, String job, String bio, int dateAgeStart, int dateAgeTop, String dateHeght, String dateReligion, String dateJob, boolean isPostApproved ) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.religion = religion;
        this.city = city;
        this.height = height;
        this.job = job;
        this.bio = bio;
        this.dateAgeStart = dateAgeStart;
        this.dateAgeTop = dateAgeTop;
        this.dateHeight = dateHeght;
        this.dateReligion = dateReligion;
        this.dateJob = dateJob;
        this.isPostApproved = isPostApproved;
        this.bio = bio;
    }


    @SerializedName("phone_number")
    private long phoneNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private int age;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("Religion")
    private String religion;

    @SerializedName("city")
    private String city;

    @SerializedName("height")
    private String height;

    @SerializedName("job")
    private String job;

    @SerializedName("bio")
    private String bio;

    @SerializedName("date_age_start")
    private int dateAgeStart;

    @SerializedName("date_age_top")
    private int dateAgeTop;

    @SerializedName("date_height")
    private String dateHeight;

    @SerializedName("date_religion")
    private String dateReligion;

    @SerializedName("date_job")
    private String dateJob;

    @SerializedName("is_post_approved")
    private boolean isPostApproved;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getDateAgeStart() {
        return dateAgeStart;
    }

    public void setDateAgeStart(int dateAgeStart) {
        this.dateAgeStart = dateAgeStart;
    }

    public int getDateAgeTop() {
        return dateAgeTop;
    }

    public void setDateAgeTop(int dateAgeTop) {
        this.dateAgeTop = dateAgeTop;
    }

    public String getDateHeight() {
        return dateHeight;
    }

    public void setDateHeight(String dateHeight) {
        this.dateHeight = dateHeight;
    }

    public String getDateReligion() {
        return dateReligion;
    }

    public void setDateReligion(String dateReligion) {
        this.dateReligion = dateReligion;
    }

    public String getDateJob() {
        return dateJob;
    }

    public void setDateJob(String dateJob) {
        this.dateJob = dateJob;
    }

    public boolean getIsPostApproved() {
        return isPostApproved;
    }

    public void setIsPostApproved(boolean isPostApproved) {
        this.isPostApproved = isPostApproved;
    }
}