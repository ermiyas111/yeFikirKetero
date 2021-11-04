package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public Post(String name, int age, String gender, String phoneNumber, String address, String religion, String height, String job, String bio, int ageStart, int ageEnd, String dateReligion, String dateHeight, String dateJob, String userId) {
        this.myInfo = new MyInfo(name, age, gender, phoneNumber, address, religion, height, job, bio);
        this.preferenceInfo = new PreferenceInfo(ageStart, ageEnd, dateReligion, dateHeight, dateJob);
        this.userId = userId;
    }

    @SerializedName("myInfo")
    private MyInfo myInfo;

    @SerializedName("preferenceInfo")
    private PreferenceInfo preferenceInfo;

    @SerializedName("id")
    private String userId;

    public MyInfo getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(MyInfo myInfo) {
        this.myInfo = myInfo;
    }

    public PreferenceInfo getPreferenceInfo() {
        return preferenceInfo;
    }

    public void setPreferenceInfo(PreferenceInfo preferenceInfo) {
        this.preferenceInfo = preferenceInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public class MyInfo{
        public MyInfo(String name, int age, String gender, String phoneNumber, String address, String religion, String height, String job, String bio) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.religion = religion;
            this.height = height;
            this.job = job;
            this.bio = bio;
        }

        @SerializedName("name")
        private String name;

        @SerializedName("age")
        private int age;

        @SerializedName("gender")
        private String gender;

        @SerializedName("phoneNumber")
        private String phoneNumber;

        @SerializedName("address")
        private String address;

        @SerializedName("religion")
        private String religion;

        @SerializedName("height")
        private String height;

        @SerializedName("job")
        private String job;

        @SerializedName("bio")
        private String bio;

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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            this.religion = religion;
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

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
    }


    public class PreferenceInfo{
        public PreferenceInfo(int ageStart, int ageEnd, String religion, String height, String job) {
            this.ageStart = ageStart;
            this.ageEnd = ageEnd;
            this.religion = religion;
            this.height = height;
            this.job = job;
        }

        @SerializedName("ageStart")
        private int ageStart;

        @SerializedName("ageEnd")
        private int ageEnd;

        @SerializedName("religion")
        private String religion;

        @SerializedName("height")
        private String height;

        @SerializedName("job")
        private String job;

        public int getAgeStart() {
            return ageStart;
        }

        public void setAgeStart(int ageStart) {
            this.ageStart = ageStart;
        }

        public int getAgeEnd() {
            return ageEnd;
        }

        public void setAgeEnd(int ageEnd) {
            this.ageEnd = ageEnd;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            this.religion = religion;
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
    }
}