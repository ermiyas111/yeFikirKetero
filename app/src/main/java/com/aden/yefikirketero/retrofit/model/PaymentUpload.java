package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class PaymentUpload {
    public PaymentUpload(long phoneNumber, long cardNumber, String isCardFilled, String paymentReason, String userId) {
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.isCardFilled = isCardFilled;
        this.paymentReason = paymentReason;
        this.userId = userId;
    }


    @SerializedName("payer_phone_number")
    private long phoneNumber;

    @SerializedName("card_number")
    private long cardNumber;

    @SerializedName("is_card_filled")
    private String isCardFilled;

    @SerializedName("payment_reason")
    private String paymentReason;

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

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long name) {
        this.cardNumber = cardNumber;
    }

    public String getIsCardFilled() {
        return isCardFilled;
    }

    public void setIsCardFilled(String isCardFilled) {
        this.isCardFilled = isCardFilled;
    }

    public String getPaymentReason() {
        return paymentReason;
    }

    public void setPaymentReason(String paymentReason) {
        this.paymentReason = paymentReason;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
