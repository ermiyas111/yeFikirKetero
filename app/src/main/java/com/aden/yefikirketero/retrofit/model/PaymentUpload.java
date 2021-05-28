package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class PaymentUpload {
    public PaymentUpload(long phoneNumber, String paymentStatus, String userId) {
        this.phoneNumber = phoneNumber;
        this.paymentStatus = paymentStatus;
        this.userId = userId;
    }


    @SerializedName("payer_phone_number")
    private long phoneNumber;

    @SerializedName("payment_status")
    private String paymentStatus;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
