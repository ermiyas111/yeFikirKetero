package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostUpload {

    public PostUpload(String name, int age, String gender, String phone, String address, String height, String religion, String bio, String job, PostUpload.Preference preference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.height = height;
        this.religion = religion;
        this.bio = bio;
        this.job = job;
        this.preference = preference;

    }


    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private int age;

    @SerializedName("gender")
    private String gender;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private String address;

    @SerializedName("height")
    private String height;

    @SerializedName("religion")
    private String religion;

    @SerializedName("bio")
    private String bio;

    @SerializedName("job")
    private String job;

    @SerializedName("preference")
    private PostUpload.Preference preference;

    public static class Preference {

        public Preference( String minAge, String maxAge, String religion, String height, String job) {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.religion = religion;
            this.height = height;
            this.job = job;
        }

        @SerializedName("minAge")
        private String minAge;

        @SerializedName("maxAge")
        private String maxAge;

        @SerializedName("religion")
        private String religion;

        @SerializedName("height")
        private String height;

        @SerializedName("job")
        private String job;

        public String getMinAge() {
            return minAge;
        }

        public void setMinAge(String minAge) {
            this.minAge = minAge;
        }

        public String getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(String maxAge) {
            this.maxAge = maxAge;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            religion = religion;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        bio = bio;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}