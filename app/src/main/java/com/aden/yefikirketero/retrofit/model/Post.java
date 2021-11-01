package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public Post(String success, ArrayList<UserData> data, int message) {
        this.success = success;
        this.data = data;
        this.message = message;

    }


    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private ArrayList<UserData> data;

    @SerializedName("message")
    private int message;

    public class UserData {


        public UserData(String userId, String name, int age, String gender, String phone, String address, String height, String religion, String Bio, String job, Preference preference) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
            this.address = address;
            this.height = height;
            this.religion = religion;
            this.Bio = Bio;
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

        @SerializedName("Bio")
        private String Bio;

        @SerializedName("job")
        private String job;

        @SerializedName("preference")
        private Preference preference;

        public class Preference {

            public Preference( String minAge, String maxAge, String Religion, String height, String job) {
                this.minAge = minAge;
                this.maxAge = maxAge;
                this.Religion = Religion;
                this.height = height;
                this.job = job;
            }

            @SerializedName("minAge")
            private String minAge;

            @SerializedName("maxAge")
            private String maxAge;

            @SerializedName("Religion")
            private String Religion;

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
                return Religion;
            }

            public void setReligion(String religion) {
                Religion = religion;
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
            return Bio;
        }

        public void setBio(String bio) {
            Bio = bio;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public Preference getPreference() {
            return preference;
        }

        public void setPreference(Preference preference) {
            this.preference = preference;
        }
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<UserData> getData() {
        return data;
    }

    public void setData(ArrayList<UserData> data) {
        this.data = data;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}