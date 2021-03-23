package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhoneNotifications {
    public PhoneNotifications(String phoneNumber, String person) {
        this.phoneNumber = phoneNumber;
        this.person = person;
    }


    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("user")
    private String person;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
