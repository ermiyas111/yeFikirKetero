package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Payment {
    public Payment(long phoneNumber, String paymentStatus, String paymentTime, String userId) {
        this.phoneNumber = phoneNumber;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
        this.userId = userId;
    }


    @SerializedName("payer_phone_number")
    private long phoneNumber;

    @SerializedName("payment_status")
    private String paymentStatus;

    @SerializedName("payment_time")
    private String paymentTime;

    @SerializedName("user")
    private String userId;

    //@SerializedName("phone_notifications")
    //private List<Post> phoneNotifications;

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
