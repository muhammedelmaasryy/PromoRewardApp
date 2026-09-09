package com.masry.fawazer.dtos;

public class PromoInquiryResponse {
    private boolean eligible;
    private String message;

    public PromoInquiryResponse() {}

    public PromoInquiryResponse(boolean eligible, String message) {
        this.eligible = eligible;
        this.message = message;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
